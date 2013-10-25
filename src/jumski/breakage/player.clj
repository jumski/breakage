(ns jumski.breakage.player)

(defn beat-to-play [beat trk]
  (let [beats (/ (count trk) 4)
        index (mod (dec beat) beats)
        start (* 4 index)
        end   (+ 4 start)
        trk   (vec trk)]
    (subvec trk start end)))

(defn split-on-keyword
  "Splits input sequence on chunks, each starting with keyword"
  [pat]
  (->> pat
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn vels
  "Returns velocities from track"
  [trk]
  (nth trk 1))

(defn pits
  "Returns pitches from track. If not found, return empty list"
  [trk]
  (if (= 2 (count trk)) [nil]
         (nth trk 2)))

(defn cycle-to-same-length
  "Accepts sequences of various length.
  Returns sequences of same length.
  Cycles shorter sequence if neccessary"
  [& seqs]
  (let [maxlen (apply max (map count seqs))]
    (map #(take maxlen (cycle %)) seqs)))

(defn force-same-length
  "Accepts sequences of various length.
  Returns sequences of same length.
  Appends nils to rests if shorter than first.
  Shortens rests if longer than first."
  [aseq & seqs]
  (let [len  (count aseq)
        seqs (map #(take len (concat % (repeat nil))) seqs)]
    (cons aseq seqs)))
    ;; [aseq
    ;;  (take maxlen (concat bseq (repeat nil)))]))

(defn hit
  "Returns hitname from track"
  [trk]
  (first trk))

(defn make-track [_])

(defn make-pattern
  "Accepts flat list of hits, velocities and pitches.
  Returns a map of hits to lists, each list
  containing a maps of velocities and pitches to values."
  [trks]
  (apply merge (map make-track trks)))
  ;; (let [trks (split-on-keyword trks)]
  ;;   (count (first trks))
    ;; (map count trks)
    ;; (map #(make-track %) trks)
    ;; (map #(println "xxxx" %) trks)
    ;; ))
