(ns jumski.breakage.pattern)

(def _ nil)

(defn normalize-track [patt]
  (let [flat (cond
          (= 3 (count patt))
          (let [vlen (count (get patt 1))
                plen (count (get patt 2))
                missing (- vlen plen)]
            (if (zero? missing) patt
              (let [tail (repeat missing _)
                    pit (concat (patt 2) tail)]
                (assoc patt 2 pit))))
          (= 2 (count patt))
          (let [len (count (last patt))
                pit (vec (repeat len _))]
            (conj (vec patt) pit)))]
    [(first flat) (vec (rest flat))]))

(defn split-on-keyword [patt]
  (->> patt
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))))

(defn track-slice [beat patt hit]
  (let [trk (hit (apply hash-map (apply concat patt)))
        ;; x (println trk)
        trklen (-> trk first count)
        ;; x (println trklen)
        beats (/ trklen 4)
        ;; x (println beats)
        index (mod beat beats)
        ;; x (println index)
        start (* 4 index)
        ;; x (println start)
        end (+ 4 start)
        ;; x (println end)
        ]
    (map #(subvec % start end) trk)))
