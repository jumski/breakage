(ns jumski.breakage.break-10
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "MS-20 mini"))

(comment
  (start-sequencing 154 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap { 1 :kick1 })
(def midimap { 1 :kick1 2 :hats1 })
(def midimap { 1 :kick1 2 :hats1 3 :hits1 })
(def midimap { 1 :kick1 2 :hats1 3 :hits1 4 :snares1 })
(def midimap { 1 :kick2 })
(def midimap { 1 :bass1 })
(def midimap { 1 :kick1 2 :hats1 3 :hits1 4 :snares1 9 :bass1 })
(def midimap { 1 :bass-exp } )

(defpatch :bass-exp
  :c2 6 . . . . . . .
  :e2 . . . . 6 . . .
  )

(defpatch :bass1
  :c0 (repeat 8 nil)
  :c2 + . . . + . . . + . . . + . . .
      + . 5 . + . . . + . 5 . + . . .
      + . . . + . . . + . . . + . . .
      + . 5 . + . . . + . 5 . + . . .
  )

(defpatch :kick2
  :ab-kick4  9 . . . . . 9 .
  )

(defpatch :snares1
  :ab-snare1  + . . . + . . . . 6 . . + . . .
  :ab-snare2  + . . . + 6 . . . . . . + . . .
  )
(defpatch :hits1
  :c0 (repeat 16 nil)
  :ab-snare3   . . 3
  :ab-snare4   . . . . 5 .
  )

(defpatch :kick1
  :dm-kick1  8 . . .
  )

(defpatch :hats1
  :c0 (repeat 8 nil)
  :ses-chat1   4 . .
  )
