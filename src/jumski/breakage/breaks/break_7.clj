(ns jumski.breakage.break-7
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

(def midimap { 1 :break1 })
(def midimap { 1 :break1 2 :hats1 })

(defpatch :hats1
  :ab-chat4   . . 6 . . . . .

  :ses-snare1   + . . 4 + . . + . . . + . . 4 .
                + . . . + . . . . . 5 . + . . .
  )

(defpatch :break1
  :ab-kick2    8 4 . . + . 8 4 + 5 . . 4 . 5 .

  ;; :ab-kick6    + . . . + . . . . . . . . . . .
  ;;              + . . . + . . . . . . . . . 8 .
  ;;
  ;; :ab-kick5    + . . . + . . . . . . . . . . .
  ;;              + . . . + . . . . . . . . . . .
  ;;              + . . . + . . . . . . . . . . .
  ;;              + . . . + . . . . . . . 8 . . .

  :ab-chat3    . 4 . . 4 . . 4
  :ab-chat4    + . . . + . . . + . . . + . . .
               + . 2 3 4 4 5 . + . . . + . . .

  ;; :ses-chat1   + . . . + . . . . . . 6 . 6 . +
  ;; :ses-snare2  + . . . + . . . . . . . 3 . 3 +

  :ab-snare1   + . . . + . . . . . . . + . . .
               + . . . + . . . . . . . + . . .
               + . . . + . . . . . . . + . . .
               + . . . + . . . 4 2 1 . + . . .

  ;; :do-snare    + . . 4 + . . . + 4 . . + . . .
  ;;              + . . . + 4 . . 4 . . . + . . .
  ;;              + . . 4 + . . . + 4 . . + . . .
               ;; + . . . + 4 . . + . . . 4 . . .
  )

