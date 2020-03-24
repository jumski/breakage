(ns jumski.breakage.break-9
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))

(comment
  (start-sequencing 108.5 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap { 2 :hats1 })
(def midimap { 1 :kicks1 2 :hats1 })
(def midimap { 1 :kicks1 2 :hats1 7 :psnares1 })
(def midimap { 1 :kicks1 2 :hats1 3 :snares1 7 :psnares1 })
(def midimap {           2 :hats1 3 :snares1 7 :psnares1 })
(def midimap {           2 :hats1 3 :snares1 4 :snares2 7 :psnares1 })
(def midimap { 9 :bass1 })
(def midimap { 1 :kicks1 2 :hats1 9 :bass1 })
(def midimap { 1 :kicks1 2 :hats1 3 :snares1 9 :bass1 })
(def midimap { 1 :kicks1 2 :hats1 3 :snares1 4 :snares2 7 :psnares1})
(def midimap { 1 :kicks1 2 :hats1 3 :snares1 4 :snares2 7 :psnares1 9 :bass1 })

(defpatch :snares1
  :ab-snare4   . . . . . 6 . .
  :ab-snare4   . . . . . 6 . .

  )

(defpatch :bass1
  :c0    + . . . + . . . + . . . + . . .
  :c2    . . 6 . . .
  )

(defpatch :snares2
  :c0 (repeat 8 nil)
  :ab-snare1   . . 5
  :ab-snare2   . 6 .
  :ab-snare3   5 . .
  )

(defpatch :snares1
  :ses-snare1   + . 3 . + . . . + . 3 . + . . .
  :ses-snare2   + . . . + . . . + . . . + . . .
                + . . . + . . . 5 . . . + . . .
  )

(defpatch :psnares1
  :c0    + . . . + . . . + . . . + . . .
  :c#5    + . 5
  :c2    + . . . . . 8 . + . . . + . . .
  :c3    + . . . + . . . + . . . + . . .
         + . . . . . . + . . 5 . + . . .
  )

(defpatch :kicks1
  :dm-kick1   8 . . . . 8 . .
  )

(defpatch :hats1
  :ses-chat2  7 . . .
  :ses-chat1  . 3 . 3 . 5 . 3
  )
