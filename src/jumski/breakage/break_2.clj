(ns jumski.breakage.break-2
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-every-sequencing
                                               stop-every-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(comment
  (start-every-sequencing 174 #(player-fn sink midimap %))
  (stop-every-sequencing)

  (def midimap {1 :break:slow})
  (def midimap {1 :break:slow        2 :hats:slow})
  (def midimap {1 :break:slow        2 :hats:slow 7 :snares:slow})
  (def midimap {1 :break:snares-only 2 :hats:slow 7 :snares:slow})
  (def midimap {1 :break:hard-kick   2 :hats:fast 7 :snares:slow 9 :stabs:thirds})
  (def midimap {9 :stabs:thirds})
  (def midimap {2 :hats:slow 9 :stabs:thirds})
  (def midimap {1 :break:hard-kick 9 :stabs:thirds})
  (def midimap {1 :break:hard-kick 2 :hats:fast   9 :stabs:thirds})
  (def midimap {1 :break:hard-kick 2 :hats:fast   7 :snares:slow 9 :stabs:thirds})
  (def midimap {1 :break:hard-kick 8 :snares:fast 9 :stabs:thirds})
  (def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast})
  (def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast 9 :stabs:thirds})
  )

(defpatch :break:slow
  :kick2    8 . . 1 + . . . + . 8 . + . 1 .
  :snare4   + . 1 . 9 . . . + . . . 9 . . .
  :snare5   + . . . + . . 4 + . . . + . . .
  )
(defpatch :break:snares-only
  :snare4   + . 1 . 9 . . . + . . . 9 . . .
  :snare5   + . . . + 2 . . + 2 . . + . . .
  )
(defpatch :break:hard-kick
  :kick2    8 . . 1 + . . . + . 8 . + . 1 .
  :snare5   + . 1 . 5 . . . + 3 . . 5 . . .
            2 . 1 . 5 . 3 . + . . . 5 . . .
  :snare3   + . . . + . . 5 + . . . + . . .
  :snare1   + . . . + 3 . . + . . . + . . .
            + . . . + . . . + 1 1 1 + . . .
            + . . . + 3 . . + . . . + . . .
            + . . . + . . . + 1 1 1 + 3 . 3
  )

(defpatch :hats:slow
  :chat4    2 . .
  )
(defpatch :hats:fast
  :chat3    3 . . .
  :chat5    . . 2 .
  )
(defpatch :snares:fast
  :c#4   . . . . . . . . . . . . . . 2 .
  :c#3   . . . . . . . . . 2 . . . . . .
  :d#3   . . . . . . . . . . 2 . . . . .
  :e#3   . . . . . . . . . . . 2 . . . .
  :g1    . . . + . . . + . . . + . . . +
  )

(defpatch :snares:slow
  :c4        + 2 . 2 + . . . + . . . + . . .
             + . . . + . . . + . . . + . . .
  :c#3       + . . . + . . . + . 4 . + . . .
  :c2        + . . . + . 5 . + . . . + . . .
  )

(defpatch :stabs:thirds
  :g1      + . . . + . . . + . . . + . . .
           + . . . + . 9 . + . . . + . . .
  :a#1     (concat
             (take 16 (cycle [nil nil 9]))
             (take 16 (repeat nil)))
  )
(defpatch :stabs:thirds2
  :g1      + . . . + . . . + . . . + . . .
           + . . . + . 9 . + . . . + . . .
  :a#1     (concat
             (take 16 (cycle [nil nil 9]))
             (take 12 (cycle [nil 8]))
             [nil nil nil nil])
  )

