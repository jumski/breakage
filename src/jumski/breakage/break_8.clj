(ns jumski.breakage.break-8
(:require [overtone.midi :refer [midi-out]]
          [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
          [jumski.breakage.state :refer [defpatch reset-state!]]
          [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))

(comment
(start-sequencing 154 #(player-fn sink midimap %))
(stop-sequencing)
)

(def midimap {                                                     9  :bass1 })
(def midimap {                               5 :hats1              9  :bass1 })
(def midimap { 1 :kicks1                     5 :hats1              9  :bass1 })
(def midimap { 1 :kicks1            4 :hits1 5 :hats1              9  :bass1 })
(def midimap { 1 :kicks1 3 :snares1 4 :hits1 5 :hats1              9  :bass1 })
(def midimap {                      4 :hits1 5 :hats1 7 :psnares1  9  :bass1 })
(def midimap { 1 :kicks1            4 :hits1 5 :hats1 7 :psnares1  9  :bass1 })
(def midimap { 1 :kicks1 3 :snares2 4 :hits1 5 :hats1 7 :psnares1  9  :bass1 })
(def midimap { 1 :kicks1 3 :snares2 4 :hits1 5 :hats1 7 :psnares1            })

(defpatch :psnares1
  ;; :c#5    + 8 . 8 . 8 . 8 . 8 . 8 + . . 8
  :c#4    + . . + . . . . . + . . + . . .
          + . . + . . . . . + . . + . . .
          + . . + . . . . . + . . + . . .
          + . . + 7 . . . 7 + . . + . . .
  :c#3    + . 7 . + . . . + . 7 . + . . .
  :a#2    + . . . + . . . + . . . + . . .
          + . . . + . . . + . . . + 7 . .
  )

(defpatch :snares1
  :ses-snare2  + . . . + . . . + . . . + . . .
               + . . . + . . . . . 6 . + . . .
               + . . . + . . . . . . . + . . .
               + . . . + . 5 . . . 6 . + . . .
  )
(defpatch :snares2
  :c0 (repeat 16 nil)
  :do-snare    . . . 5 . .

  )

(defpatch :hits1
  :c0 (repeat 8 nil)

  :ses-snare1 . 4 .
  )

(defpatch :kicks1
  :ses-kick1   8 . . . . 7 . .
  )

(defpatch :hats1
  :ses-chat1   1 3 5 1
  )

(defpatch :bass1
  ;; :g#1     + . . + . . . 6 . . . . + . . .
  ;;         + . . + . . . + . . . . + . . .

  :c#1     + . . + . . . + . . . . + . . .
           + . . . . . . . . . . 8 + . . .
  )
