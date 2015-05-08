(ns jumski.breakage.mindstorm)

(def patterns (atom {}))

(def | nil)
(def . nil)

(defn- make-track [steps]
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

(defn make-pattern [flat]
  (let [splitted (split-on-keyword flat)
        tracks (for [[hitname & steps] splitted]
                 {hitname (make-track steps)})]
    (apply merge-with concat tracks)))

(defn defpattern
  "Parses steps into a pattern and stores in patterns atom"
  [pname & body]
  (let [patt (make-pattern body)]
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
