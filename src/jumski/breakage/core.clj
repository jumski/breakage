(ns jumski.breakage.core)
(use 'overtone.live)

(definst sin-wave [freq 60 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (sin-osc freq)
     vol))

(definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (saw freq)
     vol))

(definst square-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (lf-pulse freq)
     vol))

(definst noisey [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (pink-noise) ; also have (white-noise) and others...
     vol))

(definst triangle-wave [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (lf-tri freq)
     vol))


(definst spooky-house [freq 80 width 0.2
                         attack 0.3 sustain 4 release 0.3
                         vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (square-wave (+ freq (* 30 (lf-pulse:kr 0.5 0 width))))
     vol))
;; (spooky-house)
;; (def sample-names [
;;   "26878_VEXST_Bass_stab.wav"
;;   "26879_VEXST_Closed_Hi_Hat_1.wav"
;;   "26880_VEXST_Closed_Hi_Hat_2.wav"
;;   "26881_VEXST_Closed_Hi_Hat_3.wav"
;;   "26882_VEXST_Closed_Hi_Hat_4.wav"
;;   "26883_VEXST_Crash_snare.wav"
;;   "26884_VEXST_Crash.wav"
;;   "26885_VEXST_Kick_1.wav"
;;   "26886_VEXST_Kick_2.wav"
;;   "26887_VEXST_Kick_3.wav"
;;   "26888_VEXST_Kick_4.wav"
;;   "26889_VEXST_Open_hi_hat.wav"
;;   "26890_VEXST_Reverse_Cl_Hi_Hat_2.wav"
;;   "26891_VEXST_Reverse_Kick_4.wav"
;;   "26892_VEXST_Reverse_Roll_4.wav"
;;   "26893_VEXST_Reverse_Snare_3.wav"
;;   "26894_VEXST_Semi_Kick_2.1.wav"
;;   "26895_VEXST_Semi_Kick_2.2.wav"
;;   "26896_VEXST_Semi_Snare_1.wav"
;;   "26897_VEXST_Semi_Snare_2.wav"
;;   "26898_VEXST_Semi_Snare_3.wav"
;;   "26899_VEXST_Semi_Snare_4.wav"
;;   "26900_VEXST_Snare_1.wav"
;;   "26901_VEXST_Snare_2.wav"
;;   "26902_VEXST_Snare_3.wav"
;;   "26903_VEXST_Snare_4.wav"
;;   ])
