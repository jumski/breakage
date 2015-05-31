(ns jumski.breakage.break-3
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-every-sequencing
                                               stop-every-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(comment
  (start-every-sequencing 140 #(player-fn sink midimap %))
  (stop-every-sequencing)
)


(def midimap {1 :break1 7 :snares 9 :bass})

(defpatch :break1
  :kick4   9 1 3 1 9 1 3 1 9 3 3 9 + 2 9 2
  :chat1   . 2 .
  :chat2   . . 3 .
  :snare4  + . . . + . 5 . + . . . + . . .
  :snare5  + . . . + . . . + . . . + . . .
           + . . . + . . . + . 4 . + . . .
  :snare2  2 . .
  )

(defpatch :snares
  :f3      + . . . + . . + . 1 . . + . . .
  :c3      + . . 1 . + . . + . . . + . . .
  )

(defpatch :bass
  :g1     + . 7 . + . . . + . . . + . . .
          + . 7 . + . . . + . . . + . . .
          + . 7 . + . . . + . . . + . . .
          + . 7 . + . . . + . 7 . 7 7 . 7
  )
