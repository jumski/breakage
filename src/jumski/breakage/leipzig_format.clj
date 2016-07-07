(ns jumski.breakage.leipzig-format
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(comment
(start-sequencing 154 #(player-fn sink midimap %))
(stop-sequencing)
)

(def midimap {7 :snare-pitch})

(comment
  (start-sequencing 174 #(player-fn sink midimap %))
  (stop-sequencing)
)

(defpatch :kicks2
  :ses-snare2     3 . . . . . . .
  :ab-kick2       6 . . . . . . .
  :ses-chat1      . 2
  )
