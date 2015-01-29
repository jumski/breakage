(ns jumski.breakage.synth_test_1
  (:use [overtone.live]))

;; (demo 10 (lpf (saw 100) (mouse-x 40 5000 EXP)))

;; ;; Based on Dawn by Schemawound: http://sccode.org/1-c
(defsynth fallout-wind [decay 30 attack 30 out-bus 0]
  (let [lfo  (+ 0.5 (* 0.5 (sin-osc:kr [(ranged-rand 0.5 1000) (ranged-rand 0.5 1000)] :phase (* 1.5 Math/PI))))
        lfo3 (+ 0.5 (* 0.5 (sin-osc:kr [(ranged-rand 0.1 0.5) (ranged-rand 0.1 0.5)] :phase (* 1.5 Math/PI))))
        lfo2 (+ 0.5 (* 0.5 (sin-osc:kr [(* (ranged-rand 0.5 1000) lfo lfo3) (* (ranged-rand 0.5 1000) (- 1 lfo) (- 1 lfo3))] :phase (* 1.5 Math/PI))))
        fillers (map (fn [_] (* lfo2 (sin-osc:ar (ranged-rand 40 1000) :phase 0))) (range 0 100))]
    (out:ar out-bus  (* (mix:ar fillers)
                  (env-gen:kr (perc attack decay) :action FREE)))))
;;
;;
;; (defsynth feedback-loop []
;;     (let [input (crackle 1.5)
;;                   fb-in (local-in 1)
;;                           snd (+ input (leak-dc (delay-n fb-in 20.0 (* 0.8 (mouse-x 0.001 1.05)))))
;;                                   fb-out (local-out snd)
;;                                           snd (limiter snd 0.8)]
;;       (out 0
;;         (pan2
;;           (rlpf (* 0.5 snd)
;;                 (mouse-x 10 10000)
;;                 (mouse-y 0.0001 0.9999))))))

;; (demo 10
;;       (rlpf (* 0.5 (saw [338 440]))
            ;; (mouse-x 10 10000)
            ;; (mouse-y 0.0001 0.9999))) ; cutoff frequency
