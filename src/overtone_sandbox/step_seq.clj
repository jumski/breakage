(ns overtone-sandbox.step-seq
  (:use [overtone.live]
        [overtone.inst.synth])
  (:require [overtone-sandbox.kit :as kit]))

(def amen (kit/load-kit "samples/amen-break"))

(def _ 0)
(def o 1)
(def k 1)
(def s 1)
(def h 1)
(def c 1)

(def metro (metronome 194))

(defn quarter-active?
  "Returns true if given quarter is set"
  [quarters quarter-index]
  (> (quarters quarter-index) 0))

(defn quarter-volume
  "Returns volume for given quarter"
  [quarters quarter-index]
  (let [value (+ 1 (quarters quarter-index))] ; this is ugly because non-active has vol of 1
    (/ value 10.0)))

(defn play-pattern
  "For each track in a pattern it schedules active samples to play at proper time"
  [pattern hitname beat]
  (let [quarters-in-pattern (-> pattern vals first count)
        beats-in-pattern (/ quarters-in-pattern 4)
        slice-to-play-index (mod beat beats-in-pattern)
        slice-to-play (vec (take 4 (drop (* 4 slice-to-play-index) (pattern hitname))))]
    (println slice-to-play)
    (doseq [quarter-index (range 4) :when (quarter-active? slice-to-play quarter-index)]
      (let [hit (amen hitname)
            dupa (println hit)
            volume (quarter-volume slice-to-play quarter-index)]
        (at (metro (+ (* 0.25 quarter-index) beat))
            (mono-player hit 1 0 false volume))))))

(defn player
  "Plays all tracks from given pattern"
  [pattern beat]
  (doseq [hitname (keys pattern)] (play-pattern pattern hitname beat))
  (apply-at (metro (inc beat)) #'player pattern (inc beat) []))

(def dnb-base {:kick1    [9 _ _ _ _ _ _ 9 _ _ 9 _ _ _ _ _]
               :snare2   [_ _ _ _ 9 _ _ _ _ _ _ _ 9 _ _ _]
               :chat2    [_ _ 3 _ _ _ 3 _ 3 _ _ _ _ _ 3 _]
               :csnare   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})

(def dnb-base2 {:kick1   [9 _ _ _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 9 _ _ _ _ _ _ _ 9 _ _ _]
                :chat2   [_ _ 3 _ _ _ 3 _ 3 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ _ _ 3 _ _ _ _ _ _]})

(def dnb-final {:kick1   [9 _ 9 _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 9 _ _ _ _ _ _ _ 9 _ _ _]
                :chat2   [_ _ 3 _ _ _ 3 _ 3 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ 3 _ _ _ _ _ _ _ _]})

(def dnb-final {:kick1   [9 _ 9 _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 9 _ _ _ _ _ _ _ 9 _ _ _]
                :chat2   [_ _ 3 _ _ _ 3 _ 3 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ 3 _ _ _ _ _ _ _ _]})

(def longdnb (merge-with concat dnb-base dnb-base2 dnb-base dnb-final))

(stop)
(player longdnb (metro))

