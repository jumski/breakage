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
  (start-every-sequencing 154 #(player-fn sink midimap %))
  (stop-every-sequencing)
)


(def midimap {1 :break1})

(defpatch :break1
  :kick4     9 . . . 1 . . .

  )
