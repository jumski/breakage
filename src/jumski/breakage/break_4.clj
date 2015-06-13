(ns jumski.breakage.break-4
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(comment
  (start-sequencing 155 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap {1 :break1})

(defpatch :break1
  :ab-kick6        7 . . . + . 7 . + . 7 . + . . .
                   7 . . . + . 7 . + . 7 . + . . .
                   7 . . . + . 7 . + . 7 . + . . .
                   7 . . . + . 7 . + . 7 . + 7 . .
  :ses-snare2      + . . . 6 . . 5 + . . . + . 5 .
                   + . . . 6 . . 4 + . . . + . 6 .
                   + . . . 6 . . 5 + . . . + . 5 .
                   + . . + 6 . . 4 . . . . 6 . . .
  :ab-snare4       . . 4 . . 4 . . 4 . . 4 . . 4 .
  :ses-chat1       4 . . 4 + . 4 .
  :ses-chat2       + 3 . . 3 . . 3
  )

