(ns jumski.breakage.break-11
(:require [overtone.midi :refer [midi-out]]
          [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
          [jumski.breakage.state :refer [defpatch reset-state!]]
          [jumski.breakage.helpers :refer [player-fn]]))

(reset-state!)
(def sink (midi-out "USB"))

(comment
  (start-sequencing 194 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap { 1 :kicks1 })
(def midimap { 1 :kicks2 })
(def midimap { 1 :kicks3 })

(def midimap { 1 :kicks1 10 :bass1 })
(def midimap { 1 :kicks2 10 :bass1 })
(def midimap { 1 :kicks3 10 :bass1 })

(def midimap {           10 :bass1 })

(defpatch :bass1
  :c0   (repeat 32 nil)
  :c3   . . 8
  :c2   . . . . . . . . 8 . . .
  ;; :c1   . . 8
  )

(defpatch :kicks3
  :dm-kick1    8 . 8 . + . . . 8 . . . + . 8 .
               . . + . 8 . . . + . 8 . + . . .
               8 . 8 . + . . . 8 . . . + . 8 .
               . . + . 8 . . . + . 8 . + . . 4
  :do-snare    + . . . + . . . + . . . + . . .
(defpatch :bass1
  :c0   (repeat 32 nil)
  :c1   . . . . 8
  :c2   . . . . . . . . . . 8 .
  ;; :c1   . . 8
  )               + . . . + . 5 . + . . . + . . .
  :do-chat     (take 16 (cycle [4 nil nil]))
  ;; :ab-chat2    (take 16 (cycle [nil nil nil nil nil 4]))
  :ses-snare2  + . . . + . . + 4 . . . + . 4 .
               + . . . 4 . . . + . 4 . + . . .
               + . . . + . . + 4 . . . + . 4 .
               + . . . 4 . . . + . 4 . + . . .
  :ab-chat1 . . 3 .
  ;; :ses-chat2   . 2 . 2
  :ses-roll2   2 . 2 .
  :ses-snare1  + . . . + . . . + . . . + . . .
               + . . . + . . . + . . . + . . .
               + . . . + . . . + . . . + . . .
               + . . . + . . . + . . . 3 4 . 4
  )

(defpatch :kicks2
  :dm-kick1    8 . . . 8 . . . + . 8 . + . . .
  :do-snare    + . . . + . . . + . . + 5 . . .
               + . . . + . . . + . . + 5 . . .
               + . . . + . . . + . . + 5 . . .
               + . . . + . . . + . . + 5 . 5 .
  :ab-snare4   + . . . + . . . + . . . + . . .
               + . 6 . + . . . + . . . + . . .
               + . . . + . . . + . . . + . . .
               + . 6 . + . . . 6 . . . + . . .
  :ses-snare2  + . . . + . 5 . + . . . + . . .
               + . . . + . 5 . + . . . + . . .
               + . . . + . 5 . + . . . + . . .
               + . . . + . 5 . . . . . + . . .
  :ab-chat1    4 .
  :ab-snare2   + . . . + . . 5 + 5 . 4 + 4 . .
  )

(defpatch :kicks1
  :dm-kick1    8 . . . + . 8 . + . . . 8 . . .
  :ses-kick1   . . . . 6 . . + . . 6 + . . . .
               . . . . 6 . . + . . 6 + . . . .
               . . . . 6 . . + . . 6 + . . . .
               . . . . 6 . . + . 6 . + . . . .
  :ab-chat1    4 .
  :ses-snare2  + . . . + . . . 5 . . . + . . .
               + . . . + . . . 5 . . . + . . .
  :do-snare    . . . . . . . . . . . . . . . .
               . . . . . . . . . . . . . . . .
               . . . . . . . . . . . . . . . .
               . . . . . . . . . . 5 . . . . .
  :ab-snare1   + . . . + . . . + . . . + . . .
               + . 6 . + . . . + . . . + . . .
               + . . . + . . . + . . . + . . .
               + . 6 . + . . . + . . . . . 6 .
  :ab-snare2   + . . . + 5 . 5 + . . . + . . .
  :ab-snare3   + . . . + . . . + . . . . . . .
               + . . . + . . . + . . 5 + 5 . .
  )
