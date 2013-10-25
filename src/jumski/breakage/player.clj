(ns jumski.breakage.player)

(defn beat-to-play [beat trk]
  (let [beats (/ (count trk) 4)
        index (mod (dec beat) beats)
        start (* 4 index)
        end   (+ 4 start)
        trk   (vec trk)]
    (subvec trk start end)))
