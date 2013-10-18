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

(defn make-same-length [seqs]
  (let [seqs (map sequence seqs)
        cnts (map count seqs)
        maxlen (apply max cnts)]
    (map (partial fill-with-blanks maxlen) seqs)))

(defn maxlength [aseq]
  (->>
    (for [vp aseq] (map count vp))
    flatten
    (apply max)))

(defn make-beat [pat]
  (apply hash-map (mapcat concat
  (let [splitted (split-on-keyword pat)]
    (for [[hitname vol pit] splitted]
      (let [[vol pit] (make-same-length [vol pit])]
        [hitname {:volumes vol :pitches pit}]))))))




