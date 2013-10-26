(ns jumski.breakage.sequencer
  (:use [overtone.live])
  (:require [jumski.breakage.player :as p]))

(def metro (metronome 120))

(def _ nil)

(defn play-track
  "For each track in a pattern it schedules active samples to play at proper time"
  [kit pattern hit beat]
  (doseq [[hit steps] pattern]
    (let [steps (p/beat-to-play beat steps)
          smp   (hit kit)]
      (doseq [qtr (range 4) :when (->> qtr steps :v nil? not)]
        (let [step (steps qtr)
              vel  (:v step)
              pit  (:p step)
              pit  (if (nil? pit) 1 pit)]
          (at (metro (+ (* 0.25 qtr) beat))
              (smp :rate pit :vol vel)))))))

(defn player
  "Plays all tracks from given pattern"
  [kit fnpattern beat]
  (let [pattern (p/make-pattern (fnpattern))]
    (doseq [hit (keys pattern)]
      (play-track kit pattern hit beat))
    (apply-at (metro (inc beat)) #'player kit fnpattern (inc beat) [])))

(defn play [kit pattern bpm]
  (do
    (stop)
    (metro :bpm bpm)
    (player kit pattern (metro))))

