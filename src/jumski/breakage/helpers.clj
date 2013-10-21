(ns jumski.breakage.helpers)

(def _ nil)

(defn split-on-keyword
  "Splits input sequence when approched a keyword"
  [patt]
  (->> patt
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn fill-with-blanks
  "Appends _ at the end of aseq and returns new sequence of length len"
  [len aseq]
  (let [aseq (if (nil? aseq) [] aseq)]
    (->> (repeat _) (concat aseq) (take len))))

(defn fixed-length
  "Fills all the seqs with _ up to the length of len"
  [len seqs]
  (map (partial fill-with-blanks len) seqs))

(defn find-maxlength
  "Returns maximum volume/pitch length for a given pattern slice"
  [slice]
  (->>
    (for [[_ & vp] slice] (map count vp))
    flatten
    (apply max)))

(defn make-beat
  "For a given pattern pat outputs a map where keys are hit names
  and values are maps of volumes and pitches mapped to sequences of values"
  [pat]
  (->>
    (let [splitted (split-on-keyword pat)
          maxlength (find-maxlength splitted)]
      (for [[hitname vol pit] splitted]
        (let [[vol pit] (fixed-length maxlength [vol pit])]
          [hitname {:volumes vol :pitches pit}])))
    (mapcat concat)
    (apply hash-map)))




