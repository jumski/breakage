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
  (= 1 (quarters quarter-index)))

(defn play-pattern
  "For each track in a pattern it tries to play hits for given beat-slice"
  [pattern hitname beat]
  (let [quarters-in-pattern (-> pattern vals first count)
        beats-in-pattern (/ quarters-in-pattern 4)
        slice-to-play-index (mod beat beats-in-pattern)
        slice-to-play (vec (take 4 (drop (* 4 slice-to-play-index) (pattern hitname))))]
    (doseq [quarter-index (range 4) :when (quarter-active? slice-to-play quarter-index)]
      (at (metro (+ (* 0.25 quarter-index) beat)) ((amen hitname))))))

(defn player
  "Plays one beat-long all tracks in the pattern and reapplies itself at next beat"
  [pattern beat]
  (doseq [hitname (keys pattern)] (play-pattern pattern hitname beat))
  (apply-at (metro (inc beat)) #'player pattern (inc beat) []))

(def dnb-base {:kick1    [k _ _ _ _ _ _ k _ _ k _ _ _ _ _]
               :snare1   [_ _ _ _ s _ _ _ _ _ _ _ s _ _ _]
               :chat2    [_ _ h _ _ _ h _ h _ _ _ _ _ h _]
               :csnare   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})

(def dnb-base2 {:kick1   [k _ _ _ _ _ _ _ _ _ k _ _ _ _ _]
                :snare1  [_ _ _ _ s _ _ _ _ _ _ _ s _ _ _]
                :chat2   [_ _ h _ _ _ h _ h _ _ _ _ _ h _]
                :csnare  [_ _ _ _ _ _ _ _ _ c _ _ _ _ _ _]})

(def dnb-final {:kick1   [k _ k _ _ _ _ _ _ _ k _ _ _ _ _]
                :snare1  [_ _ _ _ s _ _ _ _ _ _ _ s _ _ _]
                :chat2   [_ _ h _ _ _ h _ h _ _ _ _ _ h _]
                :csnare  [_ _ _ _ _ _ _ c _ _ _ _ _ _ _ _]})

(def longdnb (merge-with concat dnb-base dnb-base2 dnb-base dnb-final))


(stop)
(player longdnb (metro))

