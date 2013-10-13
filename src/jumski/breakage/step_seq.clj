(ns jumski.breakage.step-seq
  (:use [overtone.live]
        [overtone.inst.synth])
  (:require [jumski.breakage.kit :as kit]))

(def empty-quarter)
(def _ empty-quarter)

(def metro (metronome 120))

(defn quarter-active?
  "Returns true if given quarter is set"
  [quarters quarter-index]
  (not= empty-quarter (quarters quarter-index)))

(defn quarter-volume
  "Returns volume for given quarter"
  [quarters quarter-index]
  (let [value (+ 1 (quarters quarter-index))]
    (/ value 10.0)))

(defn play-pattern
  "For each track in a pattern it schedules active samples to play at proper time"
  [kit pattern hitname beat]
  (let [quarters-in-pattern (-> pattern vals first count)
        beats-in-pattern (/ quarters-in-pattern 4)
        slice-to-play-index (mod beat beats-in-pattern)
        slice-to-play (vec (take 4 (drop (* 4 slice-to-play-index) (pattern hitname))))]
    (doseq [quarter-index (range 4) :when (quarter-active? slice-to-play quarter-index)]
      (let [hit (kit hitname)
            volume (quarter-volume slice-to-play quarter-index)
            pitch 1]
        (at (metro (+ (* 0.25 quarter-index) beat))
            (stereo-player hit pitch 0 false volume))))))

(defn player
  "Plays all tracks from given pattern"
  [kit pattern beat]
  (doseq [hitname (keys pattern)] (play-pattern kit pattern hitname beat))
  (apply-at (metro (inc beat)) #'player kit pattern (inc beat) []))

(def dnb-base {:kick1    [9 _ _ _ _ _ _ 9 _ _ 9 _ _ _ _ _]
               :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
               :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
               :csnare   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})

(def dnb-base2 {:kick1   [9 _ _ _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2   [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ _ _ 2 _ _ _ _ _ _]})

(def dnb-final {:kick1   [9 _ 9 _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2   [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ 2 _ _ _ _ _ _ _ _]})

(def dnb-final {:kick1   [9 _ 9 _ _ _ _ _ _ _ 9 _ _ _ _ _]
                :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2   [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :csnare  [_ _ _ _ _ _ _ 2 _ 2 _ _ _ _ _ _]})

(def dnb-final2 {:kick1   [9 _ 9 _ _ _ _ _ _ _ _ _ _ _ _ _]
                 :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ _ _ _ _]
                 :chat2   [_ _ 3 _ _ _ 4 _ 4 _ 4 _ 4 _ 4 _]
                 :csnare  [_ _ _ _ _ _ _ 2 _ 2 _ 2 _ 2 _ 2]})

(def longdnb (merge-with concat
                         dnb-base dnb-base2 dnb-base dnb-final
                         dnb-base dnb-base2 dnb-base dnb-final2))
(def amen (kit/load-kit "samples/amen-break"))

(defn play [kit pattern bpm]
  (do
    (stop)
    (metro :bpm bpm)
    (player kit pattern (metro))))

(play amen longdnb 194)

