(ns jumski.breakage.pattern)

(def _ (symbol "_"))

(defn normalize [pat]
  (cond
    (= 3 (count pat))
      (let [vollen (count (pat 1))
            pitlen (count (pat 2))
            mislen (- vollen pitlen)]
        (if (zero? mislen) pat
          (let [tail (repeat mislen _)
                pit (concat (pat 2) tail)]
            (assoc pat 2 pit))))
    (= 2 (count pat))
      (let [len (count (last pat))
            pit (vec (repeat len _))]
        (conj pat pit))))
