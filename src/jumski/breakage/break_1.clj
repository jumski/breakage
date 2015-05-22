(ns jumski.breakage.break-1
  (:require [overtone.music.pitch :refer [note]]
            [overtone.music.rhythm :refer [metronome]]
            [overtone.studio.midi :refer [midi-note]]
            [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [restart-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch db]]
            [jumski.breakage.akai-s2000 :as akai]))

(comment
  (restart-sequencing 194 player-fn)
  (stop-sequencing)
  )

(def sink (midi-out "USB"))

(defn notes-for-step [patch step]
  (for [[anote steps] patch
        :let [velo (nth (cycle steps) step)]
        :when (not (nil? velo))
        :let [velo (* 12.7 (inc velo))]]
    [anote velo]))

(def midimap {1 :intro 9 :synth 10 :synth})
(def midimap {1 :intro})
(def midimap {9 :synth})

(defn player-fn [step]
  (doseq [[midi-ch patch-name] midimap
          [anote velo] (notes-for-step (@db patch-name) step)
          :let [anote (akai/tname->note anote)
                anote (note anote)]]
    (midi-note sink anote velo 80 (dec midi-ch))))

(defpatch :intro
  :chat1 7 . 3 .)

(defpatch :synth
  :e2  . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
  :f2  . 2 2 2 . . . .
       . . . . . . . .
       . 2 2 2 . . . .
       . . . . . . . .
       . 2 2 2 . . . .
       . . . . . . . .
       . 2 2 2 . . . .
       1 2 3 . 5 4 . 4
  )

(defpatch :intro
  :kick5    9 . . . . . 1 .
            . . 9 . . . . .
  :snare4   . 0.3 . . 8 . . .
  :chat1    . . 2 . . . . .
            . . . . . . 2 .
            . 2 . . . . . .
            . . . . . . . 2
  :snare5   . . 3 . . 2

  ;; :kick6    . . . 1 . . . .
  ;; :snare6   . . . . . . . .
  ;;           . . . . . . . .
  ;;           . . . . . . . .
  ;;           . . 3 . 4 . 5 .
  )

(comment
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
  :kick2    9 . . . . . . .
  :snare1   . . . . . 9 . .
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
  )
