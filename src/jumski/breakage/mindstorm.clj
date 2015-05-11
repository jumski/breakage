(ns jumski.breakage.mindstorm)

(def patterns (atom {}))

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
  "Makes hash-map of hitnames to steps from a flat list provided by defpattern"
  [flat]
  (let [raw-tracks  (split-on-keyword flat)
        avals       (map rest raw-tracks)
        akeys       (map first raw-tracks)
        maxlen      (apply max (map count avals))
        avals (map #(take maxlen %) (map cycle avals))]
    (zipmap akeys avals)))

(defn- normalize-step
  "If item is a number or keyword, return it.  Otherwise return nil"
  [item]
  (if (or (keyword? item) (number? item)) item))

(defmacro defpattern
  "Parses steps into a pattern and stores in patterns atom"
  [pname & body]
  (let [body (map normalize-step body)
        patt (make-pattern body)]
    (swap! patterns assoc pname patt)
    pname))

(defmacro getpattern
  "Gets pattern by name"
  [pname]
  (@patterns pname))

(comment
  (defpattern :intro



















    :kick1    9 . . .   . . 9 .   9 . . 8   9 . 5 .

    :hat      . 2

    :snare    . . 4 .   3 . . 3

    :tomhi    . 1 1 2   . . 3 2

    :tomlo    3 . . .



















    )
  )
