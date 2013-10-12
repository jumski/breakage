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
                  ;;  [X . . . X . . . X . . . X . . .]
(def pattern {:kick1  [k _ _ _ _ _ _ k _ _ k _ _ _ _ _]
              :snare1 [_ _ _ _ s _ _ _ _ _ _ _ s _ _ _]
              :chat1  [_ _ h _ _ _ h _ h _ _ _ _ _ h _]
              :csnare [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})

(def metro (metronome 194))

(defn play-pattern [beat hitname]
  (let [pattern-part-index (mod beat 4)
        beat-long-slice (vec (take 4 (drop (* 4 pattern-part-index) (pattern hitname))))]
    (doseq [slice-index (range 4) :when (= 1 (beat-long-slice slice-index))]
      (at (metro (+ (* 0.25 slice-index) beat)) ((amen hitname))))))

(defn player [beat]
  (doseq [hitname (keys pattern)] (play-pattern beat hitname))
  (apply-at (metro (inc beat)) #'player (inc beat) []))

;; (println (play-pattern 24 :snare1))
;;     (play-pattern 77 :snare1)
;;     (play-pattern 35 :snare1)
;;     (play-pattern 3 :snare1)
(stop)

(player (metro))
