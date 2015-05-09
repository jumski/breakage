(ns jumski.breakage.mindstorm)

(def patterns (atom {}))

(defn- make-track [steps]
  "Converts sequence of integers or nils into a map of
  sequence index to integer value, skipping any indexes
  where value is nil"
  (->> steps
    (map-indexed
      (fn [idx val]
        (if (not (nil? val))
          [idx val])))
    (filter (complement nil?))
    (map #(apply hash-map %))
    (apply merge-with concat)))

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
  (let [splitted (split-on-keyword flat)
        tracks (for [[hitname & steps] splitted]
                 {hitname (make-track steps)})]
    (apply merge-with concat tracks)))

(defn- normalize-step
  "If item is a number or keyword, return it.  Otherwise return nil"
  [item]
  (if (or (keyword? item) (number? item)) item))

(defmacro defpattern
  "Parses steps into a pattern and stores in patterns atom"
  [pname & body]
  (let [body (map normalize-step body)
        patt (make-pattern body)]
    (swap! patterns assoc pname patt)))

(comment
  (defpattern :intro



















    :kick1    9 . . .   . . 9 .   9 . . 8   9 . 5 .

    :hat      . 2

    :snare    . . 4 .   3 . . 3

    :tomhi    . 1 1 2   . . 3 2

    :tomlo    3 . . .



















    )
)
