(ns jumski.breakage.tests.break-1
  (:use [overtone.live :as o :only [metronome midi-note note stop]])
  (:use [overtone.midi :only [midi-out]])
  (:use [jumski.breakage.tests.sequencer :as s])
  (:use [jumski.breakage.mindstorm :only [defpatch patterns]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai])
  (:use [jumski.breakage.tests.step-sequences :as stepseq]))

(comment
  (def metro (metronome 154))
  (s/restart-sequencing 154 player-fn)
  (s/stop-sequencing)
  (s/play-and-advance player-fn)
  (def x (loop-pattern! 154 player-fn))
  )

(def sink (midi-out "USB"))

;; (defn notes-for-step [step]
;;   (for [[anote steps] (:intro @patterns)
;;         :let [velo (nth (cycle steps) step)]
;;         :when (not (nil? velo))
;;         :let [velo (* 12.7 (inc velo))]]
;;     [anote velo]))

(defn player-fn [step]
  (doseq [[anote velo] (notes-for-step step)
          :let [anote (akai/tname->note anote)
                anote (note anote)]]
    (midi-note sink anote velo 100 0)))

(def ch0 (akai/make-player "USB" 0))
(def ch1 (akai/make-player "USB" 1))

(defpatch :intro
  :e3   2 . . . 4 . . .
  ;; :f3   . 1
  :g#3  . . 4
  :c4   . . . . . . . .
        . . . . . . 4 .
  :e#3  . 3 .
  ;; :g#3  . . . . . 6
  )

(defpatch :intro
  :kick5     6 . 3 . 8 . 4 .
  :snare1    . . . . 5 . . .   . . . . . . . .
  :snare7    . . . . . . . .   . 9 . . 9 . . 9
             . . . . . . . .   . 9 . . . . . .
  :snare8    . 9 . . 9 . . 9   . . . . . . . .
             . 9 . . . . . .   . . . . . . . .
             ;; . . . . . . . .
             ;; . 9 . . . . . .
             ;; . . . . . . . .
             ;; . 9 . . . . . .
  )

  ;; :kick1  9 . 3 9 . . 9 .
  ;;
  ;; :snare1 . . . . . 9 . .
  ;;         . . . . . 1 . .
  ;; :snare3 . . . . 5
  ;; :snare1 . . 7
  ;; :snare1 . 2 . 3 . 7 . .
  ;;         . 2 . 3 . 7 . .
  ;;         . 2 . 3 . 7 . .
  ;;         . . 5 . . 7 . .
  ;; :chat1  2 . 4 . . 3
  ;; :chat3  . . . . 4 . . .
  ;; ;; :chat3  . 2 .
  ;; ;; :chat2  . 4
  ;; :kick6
  ;;         4 4 4 4 4 . . .
  ;;         . . . . . . . .
  ;;         . . . . . . . .
  ;;         . . . . . . . .
  ;; ;; :chat1 3 2 1
  ;; ;; :csnare . 3 . 3
  ;; )
