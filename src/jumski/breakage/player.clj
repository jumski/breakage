(ns jumski.breakage.player)

(defn beat-to-play [beat trk]
  (nth
    (->> trk cycle (partition 4 4))
    (dec beat)))
