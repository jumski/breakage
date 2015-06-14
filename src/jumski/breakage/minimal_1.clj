(ns jumski.breakage.minimal-1
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))

(comment
  (start-sequencing 174 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap { 1 :break1 })
(def midimap { 1 :break1 9 :reece1})
(def midimap {           9 :reece1})

;; (defpatch :break1
;;   :do-kick      6 . . .
;;   :ses-chat1    5 3 . 3 5 . 3 3
;;   ;; :ses-roll1    1 . 2 . . 3 . 4
;;   :ses-snare1   + . . . + . 4 . + . . . + . . .
;;                 + . . . + . 4 . + . . . + 4 . 3
;;                 + . . . + . 4 . + . . . + . . .
;;                 + . . . + . 4 . + . . . + 4 . 3
;;   :do-snare     . . + . 8 . + . . . + 8 . . . .
;;
;;   :ab-snare1    + 4 . . + . . . + 4 . . + . 3 .
;;   :ab-snare2    . . + 4 . . + . . . + 4 . . + .
;;  )
(defpatch :break1
  :ab-kick2    8 4 . . + . 8 4 + 5 . . 4 . 5 .

  :ab-kick6    + . . . + . . . . . . . . . . .
               + . . . + . . . . . . . . . 8 .

  :ab-kick5    + . . . + . . . . . . . . . . .
               + . . . + . . . . . . . . . . .
               + . . . + . . . . . . . . . . .
               + . . . + . . . . . . . 8 . . .

  :ab-chat3    . 4 . . 4 . . 4

  :ses-chat1   + . . . + . . . + . . 6 + 6 . .
  :ses-snare1  + . . . + . . . 5 . . . + . . .
               + . . . + . . . 5 . . . + . . .
               + . . . + . . . 5 . . . + . . .
               + . . . + . . . 5 . 5 . + . . .
  :ses-snare2  + . . . + . . . + . . . 3 . 3 .

  :ab-snare1   + . . . + . . . . . . . + . . .
               + . . . + . . . . . . . + . . .
               + . . . + . . . . . . . + . . .
               + . . . + . . . 4 2 1 . + . . .

  :do-snare    + . . 4 + . . . + 4 . . + . . .
               + . . . + 4 . . 4 . . . + . . .
               + . . 4 + . . . + 4 . . + . . .
               + . . . + 4 . . + . . . 4 . . .
  )

(defpatch :reece1
  :g2   . 4 . + . . . + . . . 4 . . . .
        . 4 . + . . . + . . . 4 . . . .
  :g1   + . 4 . + . . . + . . . 4 . . .
        + . 4 . + . . . + . . . 4 . . .
  )
