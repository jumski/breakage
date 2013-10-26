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

(defn cycle-to-same-length
  "Takes sequences of various length.
  Returns sequences of same length.
  Cycles shorter sequence if neccessary.
  If any sequence is nil?, converts it to [nil]"
  [& seqs]
  (let [seqs   (map #(if (nil? %) [nil] %) seqs)
        maxlen (apply max (map count seqs))]
    (map #(take maxlen (cycle %)) seqs)))

(defn force-same-length
  "Takes sequences of various length.
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

(defn build-steps
  "Takes vels and pits.
  Returns a list of maps (steps) of :vel to vels values
  and :pit to pits values"
  [vels pits]
  (let [[vels pits] (cycle-to-same-length vels pits)
        lst         (interleave vels pits)
        lst         (interleave (cycle [:vel :pit]) lst)
        steps       (partition 4 lst)]
    (map (partial apply hash-map) steps)))


(defn make-track
  "Takes list of hit, velocities and pitches.
  Returns a map of hit to steps."
  [[hit vels pits]]
  {hit (build-steps vels pits)})

(defn normalize-tracks [trks]
  "Takes sequence of tracks trks.
  Returns sequence of tracks with velocities cycled
  to the length of the longest one"
  (let [spltd (split-on-keyword trks)
        vels  (map #(nth % 1) spltd)
        nvels (apply cycle-to-same-length vels)]
    (map (fn [trk nvel] (assoc trk 1 nvel)) spltd nvels)))

(defn make-tracks
  "Takes flat list of multiple hits, velocities and pitches
  Returns sequence of maps of hit to vels and pits."
  [flat]
  (let [trks (normalize-tracks flat)]
    (map make-track trks)))

(defn merge-tracks
  "Takes variable number of maps.
  Returns map merged with concat."
  [& trks]
  (apply merge-with concat trks))

(defn make-pattern
  "Takes flat list of hits, velocities and pitches.
  Returns a map of hits to lists of steps"
  [flat]
  (let [trks (make-tracks flat)]
    (apply merge-tracks trks)))
