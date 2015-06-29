(ns jumski.breakage.roland-1
(:require [overtone.midi :refer [midi-out]]
          [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
          [jumski.breakage.state :refer [defpatch reset-state!]]
          [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))

(comment
(start-sequencing (* 2 86.5) #(player-fn sink midimap %))
(stop-sequencing)
)

(def midimap { 4 :dinga1 })
(def midimap { 5 :bass1 4 :dinga1 })
(def midimap { 9 :bass1  })
(def midimap { 5 :bass1 4 :dinga1 9 :bass1 })

(defpatch :bass1
  :g#3    + . . . + . . . + . . . + . . .
          + . . . + . . . + . . . + . . .
          + . . . + . . . + . . . + . . .
          + . . . + . 5 . + . . . + . . .
  :f#2    . 4 . 5
  :f#1    6 . 6 8
  )

(defpatch :dinga1
  :c0    (repeat 8 nil)
  :c3     7 . . . 7 . . .
  :c2     . . 7 . . .

  :c4     9 . . . . .
  )
