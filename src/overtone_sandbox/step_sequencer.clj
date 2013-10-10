(ns overtone-sandbox.step-sequencer
  (:use [overtone.live]
        [overtone-sandbox.amen]))

(def pat {:kick1  [1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0]
          :snare1 [0 0 0 0 1 0 0 0 0 0 0 0 1 0 0 0]
          :chat1  [0 0 1 0 0 0 0 0 0 1 0 0 0 1 0 0]})

(def metro (metronome 174))

(defn play-pat [beat i]
  (let [t (mod beat 4)
        p (vec (take 4 (drop (* 4 t) (pat i))))]
    (if (= 1 (p 0)) (at (metro (+ 0.00 beat)) ((overtone-sandbox.amen/kit i))))
    (if (= 1 (p 1)) (at (metro (+ 0.25 beat)) ((overtone-sandbox.amen/kit i))))
    (if (= 1 (p 2)) (at (metro (+ 0.50 beat)) ((overtone-sandbox.amen/kit i))))
    (if (= 1 (p 3)) (at (metro (+ 0.75 beat)) ((overtone-sandbox.amen/kit i))))))

(defn player [beat]
  (doseq [i (keys kit)] (play-pat beat i))
  (apply-at (metro (inc beat)) #'player (inc beat) []))

(player (metro))
;;
;; (metro :bpm 160)
;; (metro :bpm 90)
;;
;; (defn player [beat] "stop")
