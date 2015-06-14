(ns jumski.breakage.break-6
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "DROP OUT"

(reset-state!)
(def sink (midi-out "USB"))

(comment
  (start-sequencing 172 #(player-fn sink midimap %))
  (stop-sequencing)
)

(def midimap { 1 :kicks1  2 :hats1 3 :snares1                                             })
(def midimap { 1 :kicks1  2 :hats1 3 :snares1                          7 :psnares1        })

(defpatch :kicks1
  :c0           (repeat 16 nil)
  :ses-kick1    6 . . . . .
  :ab-kick2     8 . . . . .
  ;; :ses-kick2    8 . 4 . 6 . 8 .
  )

(defpatch :snares1
  :ses-snare1   + . . . 5 . . . + . . . + . . .
                + . . . + . 5 . + . . . 3 . . .
                + . 4 . + . . + 5 . . . + . . .
                + . . . 5 . . . . . 5 . + . . .
                ;; + . . . + . . . + . . . + . . .
  )

(defpatch :psnares1
  :c#2    + . . . + . . . + . . . + . . .
  :c2     + . . . + . . . + . . . + . . .
  :b2     + . . . + . . . + . . . + . . .
  :a#2    + . . . + . 2 . + . . . + . . .
          + . . . + . 2 . + . . . + . . .
          + . . . + . 2 . + . . . + . . .
          + . . . + 2 2 2 + . . . + . . .
  :a2     + . . . + . . . + . . . + . . .
  :g#2    + . . 4 + . . + . 4 . . + . . .
          + . . 4 + . . + . 4 . . + . . .
          + . . 4 + . . + . 4 . . + . . .
          + . . 4 + . . + . 4 . . 4 . 4 .
  :g2     + . . . + . . . + . . . + . . .
  :f#2    + . . . + . . . + . . . + . . .
  :f2     . . 5 . 2 . + . 4 . + . 5 . + .
  :e2     + . . . + . . . + . . . + . . .
  :d#2    + . . . + . . . + . . . + . . .
  :d2     + . . . + . . . + . . . + . . .
  :c#2    + . . . + . . . + . . . + . . .
  :c2     + . . . + . . . + . . . + . . .
  )

(defpatch :hats1
  :ses-chat1    . 4 . . 4 . . .
  :ses-roll1    . . . . . . 3 .
  )

(comment
  (start-sequencing 155 #(player-fn sink midimap %))
  (stop-sequencing)
)

