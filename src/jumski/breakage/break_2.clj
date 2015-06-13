(ns jumski.breakage.break-2
  (:require [overtone.midi :refer [midi-out]]
            [jumski.breakage.sequencer :refer [start-sequencing
                                               stop-sequencing]]
            [jumski.breakage.state :refer [defpatch reset-state!]]
            [jumski.breakage.helpers :refer [player-fn]]))

; requires akai floppy "AMEN KIT CLOJURE"

(reset-state!)
(def sink (midi-out "USB"))
(def midimap {})

(comment
  (start-sequencing 154 #(player-fn sink midimap %))
  (stop-sequencing)
  )


(def midimap {1 :break:slow})
(def midimap {1 :break:slow        2 :hats:slow})
(def midimap {1 :break:slow        2 :hats:slow 7 :snares:slow})
(def midimap {1 :break:snares-only 2 :hats:slow 7 :snares:slow})
(def midimap {1 :break:hard-kick   2 :hats:fast 7 :snares:slow 9 :stabs:thirds-low})
(def midimap {9 :stabs:thirds-low})
(def midimap {2 :hats:slow 9 :stabs:thirds})
(def midimap {1 :break:hard-kick 9 :stabs:thirds})
(def midimap {1 :break:hard-kick 2 :hats:fast   9 :stabs:thirds})
(def midimap {1 :break:hard-kick 2 :hats:fast   7 :snares:slow 9 :stabs:thirds})
(def midimap {1 :break:hard-kick 8 :snares:fast 9 :stabs:thirds})
(def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast})
(def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast 9 :stabs:thirds})
(def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast 9 :stabs:thirds 10 :drop-out:1})
(def midimap {1 :break:hard-kick 7 :snares:slow 8 :snares:fast 10 :drop-out:1})
(def midimap {1 :break:hard-kick 2 :hats:fast 7 :snares:slow 8 :snares:fast 10 :drop-out:1})
(def midimap {1 :break:hard-kick 2 :hats:fast 7 :snares:slow 8 :snares:fast 9 :stabs:thirds-low 10 :drop-out:1})
(def midimap {1 :break:hard-kick 9 :stabs:thirds-low 10 :drop-out:1})
(def midimap {10 :drop-out:1})

(defpatch :drop-out:1
  :c1       + . . + . . . . + . . . + . . .
            + . . + 3 . . . + . . . + . . .
            + . . + . . . . + . . . + . . .
            + . . + 3 . . . . . 3 . + . . .
  )

(defpatch :break:slow
  ;; :ab-kick2    (reverse (range 10)) . . . . . .
  ;; :ab-chat1    . . . . . . (range 10)
  :ab-kick2    8 . . 2 + . . . + . 8 . + . 1 .
  :ab-snare4   + . 1 . 9 . . . + . . . 9 . . .
  :ab-snare5   + . . . + . . . + . . . + . . .
            + . . . + . . . + . . . + . . .
            + . . . + . . . + . . . + . . .
            + . . . + . . 4 + . . . + . . .
  )
(defpatch :break:snares-only
  :ab-snare4   + . 1 . 9 . . . + . . . 9 . . .
  :ab-snare5   + . . . + 2 . . + 2 . . + . . .
  )
(defpatch :break:hard-kick
  :ab-kick2    8 . . 1 + . . . + . 8 . + . 1 .
  :ab-snare5   + . 1 . 5 . . . + 3 . . 5 . . .
            2 . 1 . 5 . 3 . + . . . 5 . . .
  :ab-snare3   + . . . + . . 5 + . . . + . . .
  :ab-snare1   + . . . + 3 . . + . . . + . . .
            + . . . + . . . + 1 1 1 + . . .
            + . . . + 3 . . + . . . + . . .
            + . . . + . . . + 1 1 1 + 3 . 3
  )

(defpatch :hats:slow
  :ab-chat4    2 . 3 .
  )
(defpatch :hats:fast
  :ab-chat3    3 . . .
  :ab-chat5    . . 2 .
  )
(defpatch :snares:fast
  :c#4   . . . . . . . . . . . . . . 2 .
  :c#3   . . . . . . . . . 2 . . . . . .
  :d#3   . . . . . . . . . . 2 . . . . .
  :e#3   . . . . . . . . . . . 2 . . . .
  :g1    . . . + . . . + . . . + . . . +
  )

(defpatch :snares:slow
  :c4        + 3 . 3 + . . . + . . . + . . .
             + . . . + . . . + . . . + . . .
  :c#3       + . . . + . . . + . 4 . + . . .
  :c2        + . . . + . 8 . + . . . + . . .
  )

(defpatch :stabs:thirds
  :g1      + . . . + . . . + . . . + . . .
           + . . . + . 9 . + . . . + . . .
  :a#1     (concat
             (take 16 (cycle [nil nil 9]))
             (take 16 (repeat nil)))
  )

(defpatch :stabs:thirds-low
  :g1      + . . . + . . . + . . . + . . .
           + . . . + . 9 . + . . . + . . .
  :a#1     + . 9 . + . . . . . . . + . . .
           + . . . + . . . + . . . + . . .
           + . 9 . + . . . . . . . + . 9 .
           + . . . + . . . + . . . + . . .
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

