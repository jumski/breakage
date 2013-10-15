(ns jumski.breakage.pattern)

(def _ (symbol "_"))

(defn normalize [patt]
  (cond
    (= 3 (count patt))
      (let [vlen (count (patt 1))
            plen (count (patt 2))
            missing (- vlen plen)]
        (if (zero? missing) patt
          (let [tail (repeat missing _)
                pit (concat (patt 2) tail)]
            (assoc patt 2 pit))))
    (= 2 (count patt))
      (let [len (count (last patt))
            pit (vec (repeat len _))]
        (conj patt pit))))

(defn split-on-keyword [patt]
  (->> patt
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))))
