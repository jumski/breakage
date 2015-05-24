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

(def midimap {1 :intro  9  :synth})
(def midimap {1 :intro  10 :synth2})
(def midimap {1 :break1 10 :synth2})
(def midimap {1 :intro  9  :synth 10 :synth2})
(def midimap {1 :break1 9  :synth 10 :synth2})
(def midimap {1 :break1 2 :intro })
(def midimap {1 :break1 2 :intro 9  :synth 10 :synth2})
(def midimap {9 :synth})

(defn player-fn [step]
  (doseq [[midi-ch patch-name] midimap
          [anote velo] (notes-for-step (@db patch-name) step)
          :let [anote (akai/tname->note anote)
                anote (note anote)]]
    (midi-note sink anote velo 200 (dec midi-ch))))

(defpatch :synth2
  :e2 . 3 . . 3 . . 3
  :c3 . . . 2 . . . .
      . . . . . . . .
      . . . . . . 1 .
      . . . . . . . .
  )

(defpatch :break1
  :kick1     8 . . . 8 . . .
  :kick2     . . 6 . . . 6 .
  :chat3     . . . 4 . . . 4
  :snare1    . . . . . . . .
             . . . . . . . .
             . . . . . . . .
             . . 9 . . . . .
  :chat5     . 5 . 2 . 2 1 .
  :snare2    . . . . . . . .
             . . . . . . . .
             . . . . . . . .
             . . 2 3 4 5 6 7
  )

(defpatch :intro
  :chat1 7 . . .)

(defpatch :synth
  :c2  . . . . . . 5 .
       . . . . . . 5 .
       . . . . . . 5 .
       . . 5 . . . 5 .
  :c#2 . . . . . . . .
       . . . . . . . .
       . . . . . . . .
       . . . . . . . .
       . . . 2 . . . .
       . . . . . . . .
  )

(defpatch :intro
  :kick5    9 . . . . . 1 .
            . . 9 . . . . .
  :snare4   . 1 . . 8 . . .
  :chat1    . . 2 . . . . .
            . . . . . . 2 .
            . 2 . . . . . .
            . . . . . . . 2
  :snare5   . . 3
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
