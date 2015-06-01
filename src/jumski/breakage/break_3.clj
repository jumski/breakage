(ns jumski.breakage.break-3
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(def x (atom 0))
(reset! x 10)

(comment
  (start-sequencing 154 #(player-fn sink midimap %))
  (stop-sequencing)
)


(def midimap { 1 :break1 })
(def midimap { 1 :break1 2 :hats1 })
(def midimap { 1 :break1 2 :hats1 3 :snares1 })
(def midimap { 1 :break1 2 :hats1 3 :snares2 })

(defpatch :break1
  :kick-drop    2 . . . + . 9 . + . . . 9 . . .
  :snare-drop   + . . . 9 . . . + . 9 . + . . .
                + . . . 9 . . 2 3 4 9 . + . . .
                + . . . 9 . . . + . 9 . + . . .
                + . . . 9 . . . + . 9 . + . 9 .
  )

(defpatch :hats1
  :chat-drop    + . 6 . + . 6 . 6 . . . + . 6 .
  )

(defpatch :snares1
  :c0           + . . . + . . . + . . . + . . .
  :snare6       . . . . . 4
  :snare5       . . 4 . . .
  :snare1       + . . . + . . . + . . . + . . .
                + . . . + . . + 5 . . . + . . .
  )

(defpatch :snares2
  :c0           + . . . + . . . + . . . + . . .
  ;; :snare6       . . . . . 4
  :snare5       . . 4 . . .
  :snare1       + . . . + 4 . . + 4 . . + . . .
                + . . . + . . + 5 . . . + . . .
  )
