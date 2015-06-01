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
  (start-every-sequencing 194 #(player-fn sink midimap %))
  (stop-every-sequencing)
)


(def midimap {1 :break2})

(defpatch :break1
  :kick-drop    9 . . . + . . 4 + . 9 . + . . .
                9 . . 5 + . . 3 + . 9 . + . 3 .
  :snare-drop   + . . . 8 . . . + . . . 8 . . .
  )

(defpatch :break2
  :kick-drop    8 . . . + . 8 . + . . . 8 . . .
  :snare-drop   + . . + 7 . . . + . 7 . + . . .
                + . . + 7 . . . + . 7 . + . . .
                + . . + 7 . . . + . 7 . + . . .
                + . . + 7 . . . + . 7 . + . 7 .
  )
