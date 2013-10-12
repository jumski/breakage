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

(defn hit-present?
  "Returns true if given hit-index in hits is marked as 1"
  [hits hit-index]
  (= 1 (hits hit-index)))

(defn play-pattern
  "For each track in a pattern it tries to play hits for given beat-slice"
  [pattern hitname beat]
  (let [pattern-part-index (mod beat 4)
        pattern-slice (vec (take 4 (drop (* 4 pattern-part-index) (pattern hitname))))]
    (doseq [hit-index (range 4) :when (hit-present? pattern-slice hit-index)]
      (at (metro (+ (* 0.25 hit-index) beat)) ((amen hitname))))))

(defn player
  "Plays one beat-long all tracks in the pattern and reapplies itself at next beat"
  [pattern beat]
  (doseq [hitname (keys pattern)] (play-pattern pattern hitname beat))
  (apply-at (metro (inc beat)) #'player pattern (inc beat) []))





                    ;;  [X . . . X . . . X . . . X . . .]
(def dnb-break {:kick1  [k _ _ _ _ _ _ k _ _ k _ _ _ _ _]
                :snare1 [_ _ _ _ s _ _ _ _ _ _ _ s _ _ _]
                :chat1  [_ _ h _ _ _ h _ h _ _ _ _ _ h _]
                :csnare [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})





(stop)
(player dnb-break (metro))
