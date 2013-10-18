(ns jumski.breakage.helpers)

(def _ nil)

(defn split-on-keyword [patt]
  (->> patt
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn fill-with-blanks [len aseq]
  (let [aseq (if (nil? aseq) [] aseq)]
    (->> (repeat _) (concat aseq) (take len))))

(defn fixed-length [len seqs]
  (map (partial fill-with-blanks len) seqs))

(defn find-maxlength [aseq]
  (->>
    (for [[_ & vp] aseq] (map count vp))
    flatten
    (apply max)))

(defn make-beat [pat]
  (->>
    (let [splitted (split-on-keyword pat)
          maxlength (find-maxlength splitted)]
      (for [[hitname vol pit] splitted]
        (let [[vol pit] (fixed-length maxlength [vol pit])]
          [hitname {:volumes vol :pitches pit}])))
    (mapcat concat)
    (apply hash-map)))




