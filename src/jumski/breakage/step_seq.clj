(ns jumski.breakage.step-seq
  (:use [overtone.live]
        [overtone.inst.synth]
        [jumski.breakage.pattern]))

(def metro (metronome 120))

(defn qtr-active?
  "Returns true if given quarter is set"
  [slice index]
  (let [vols (first slice)]
    (-> index vols nil? not)))

(defn qtr-volume
  "Returns volume for given quarter"
  [slice index]
  (let [vols (first slice)]
    (vols index)))

(defn qtr-pitch
  "Returns pitch for given quarter"
  [slice index]
  (let [pitches (last slice)
        pitch (pitches index)]
    (if (nil? pitch) 1
      (+ 1 (/ pitch 10.0)))))

(defn play-pattern
  "For each track in a pattern it schedules active samples to play at proper time"
  [kit fnpattern hitname beat]
  (let [pattern (fnpattern)
        pattern (split-on-keyword pattern)
        pattern (map normalize-track pattern)
        beat-to-play (track-slice beat pattern hitname)]
    (doseq [qtr (range 4) :when (qtr-active? beat-to-play qtr)]
      (let [hit (kit hitname)
            vol (qtr-volume beat-to-play qtr)
            pitch (qtr-pitch beat-to-play qtr)]
        (at (metro (+ (* 0.25 qtr) beat))
            (hit :rate pitch :vol vol))))))
;; (for [qtr (range 4) :when (qtr-active? beat-to-play qtr)]
;;   (let [hit (kit hitname)
;;         vol (qtr-volume beat-to-play qtr)
;;         pitch 1]
    ;; (at (metro (+ (* 0.25 qtr) beat))
    ;;     (hit :rate 1 :vol vol))))))


(defn player
  "Plays all tracks from given pattern"
  [kit fnpattern beat]
  (let [pattern (fnpattern)
        pattern (split-on-keyword pattern)
        pattern (map normalize-track pattern)
        pattern (pattern-to-map pattern)
        hitname (keys pattern)]
    (doseq [hitname (keys pattern)] (play-pattern kit fnpattern hitname beat))
    (apply-at (metro (inc beat)) #'player kit fnpattern (inc beat) [])))

(defn play [kit pattern bpm]
  (do
    (stop)
    (metro :bpm bpm)
    (player kit pattern (metro))))

