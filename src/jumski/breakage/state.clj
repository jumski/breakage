(ns jumski.breakage.state)

(def db
 "Stores patches, patterns and chains."
 (atom {}))

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
  [pname & bodyopts]
  (let [[opts body] (if (map? (first bodyopts))
                      [(first bodyopts) (rest bodyopts)]
                      [{} bodyopts])
        body (map #(if (list? %) (eval %) %) body)
        body (flatten body)
        body (map normalize-step body)
        patch {:opts opts :body (vec body)}]
    `(swap! db assoc ~pname ~patch)))

(defpatch :name
  :k1 1 1 (take 6 (cycle [3 4 5]))
  :p1 2 (take 7 (cycle [1 nil 2])))

(comment
  (defn player-fn [step]
    (doseq [[midi-ch patch-name] midimap
            [anote velo] (notes-for-step (@db patch-name) step)
            :let [anote (akai/tname->note anote)
                  anote (note anote)]]
      (midi-note sink anote velo 80 (dec midi-ch))))


  )
