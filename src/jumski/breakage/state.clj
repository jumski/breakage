(ns jumski.breakage.state)

(def db (atom {}))

(defn- split-on-keyword
  "Splits input sequence on chunks, each starting with keyword"
  [pat]
  (->> pat
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn make-pattern
  "Makes hash-map of hitnames to steps from a flat list provided by defpatch"
  [flat]
  (let [raw-tracks  (split-on-keyword flat)
        avals       (map (comp rest flatten) raw-tracks)
        akeys       (map first raw-tracks)
        maxlen      (apply max (map count avals))
        cycled      (->> avals (map cycle))
        avals       (map #(take maxlen %) cycled)
        avals       (map vec avals)]
    (zipmap akeys avals)))

(defn- normalize-step
  "If item is a number or keyword, return it. Otherwise return nil"
  [item]
  (if (or (keyword? item) (number? item)) item))

(defmacro defpatch
  "Parses steps into a pattern and stores in db atom"
  [pname & body]
  (let [body (map normalize-step body)
        patt (make-pattern body)]
    `(swap! db assoc ~pname ~patt)))
