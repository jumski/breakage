(ns jumski.breakage.helpers)

(def _ nil)

(defn- split-on-keyword [patt]
  (->> patt
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn make-beat [pat]
  (apply
    hash-map
    (first (for [[hitname vol pit] (split-on-keyword pat)]
             [hitname {:volumes vol
                       :pitches (repeat (count vol) _)}]))))

