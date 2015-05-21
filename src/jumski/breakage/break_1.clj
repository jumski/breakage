(ns jumski.breakage.break-1
  (:require [overtone.music.pitch :refer [note]]
            [overtone.music.rhythm :refer [metronome]]
            [overtone.studio.midi :refer [midi-note]]
            [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [restart-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch db]]
            [jumski.breakage.akai-s2000 :as akai]))

(comment
  (restart-sequencing 174 player-fn)
  (stop-sequencing)
  )

(def sink (midi-out "USB"))

(defn notes-for-step [patch step]
  (for [[anote steps] (:intro @db)
        :let [velo (nth (cycle steps) step)]
        :when (not (nil? velo))
        :let [velo (* 12.7 (inc velo))]]
    [anote velo]))

(defn player-fn [step]
  (let [patch (:intro @db)]
    (doseq [[anote velo] (notes-for-step patch step)
            :let [anote (akai/tname->note anote)
                  anote (note anote)]]
      (midi-note sink anote velo 200 0))))

(defpatch :intro
  :kick1  1 . . .
  :snare1 . 1 . .
  :chat1  . . 1 .
  :chat2  . . . 1)

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
