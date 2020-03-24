(ns jumski.breakage.break-5
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

(def midimap {           3 :hats1                                                                 })
(def midimap {           3 :hats1 4 :bg-snares                                                    })
(def midimap { 1 :kicks1 3 :hats1 4 :bg-snares  5 :main-snares                   7 :pitch-snares1 })
(def midimap { 1 :kicks2 3 :hats1 4 :bg-snares  5 :main-snares                   7 :pitch-snares1 })
(def midimap { 1 :kicks1 3 :hats1 4 :bg-snares2 5 :main-snares                                    })
(def midimap { 1 :kicks1 3 :hats1 4 :bg-snares2 5 :main-snares 6 :second-snares                   })
(def midimap {           3 :hats1 4 :bg-snares2 5 :main-snares 6 :second-snares  7 :pitch-snares2 })
(def midimap { 1 :kicks3 3 :hats1 4 :bg-snares2 5 :main-snares 6 :second-snares  7 :pitch-snares2 })
(def midimap { 1 :kicks3 3 :hats1 4 :bg-snares2 5 :main-snares 6 :second-snares  7 :pitch-snares2 })
(def midimap { 1 :kicks1 3 :hats1 4 :bg-snares2 5 :main-snares 6 :second-snares  7 :pitch-snares2 })
(def midimap { 1 :kicks3 3 :hats1 4 :bg-snares2                6 :second-snares  7 :pitch-snares2 })

(defpatch :second-snares
  :do-snare    + . . . + 2 . 2 + . 2 2 + . . .
  ;; :ab-snare2   . . 3
  )

(defpatch :kicks1
  :ses-kick2    6 . . + 4 . 6 . + . . . 6 . . .
  :ab-kick1     . . 2 . . . + . 2 . + . . . 2 .
  )
(defpatch :kicks2
  :ses-kick2    5 . . 3 + . 5 . + . . . 5 . . .
                5 . . 3 + . 5 . + . . . 5 . . .
                5 . . 3 + . 5 . + . . . 5 . . .
                5 . . 3 + . 5 . + . 5 . + . 5 .
  )

(defpatch :kicks3
  :ab-kick2     6 . . 6 + . 6 . + . . . 6 . . .
  :ses-kick2    . . . . + . . . + 4 . . . . . .
  )

; (def midimap {1 :main-snares})
(defpatch :main-snares
  :ses-snare1   + . 4 . + . . . + . . 3 + . . .
                + . 4 . + . . . + . . . + . . .
                + . 4 . + . . 2 + . . . + . . .
                + . 4 . + . . . + . . . + . . .

  :ses-snare2   + . . . + . 4 . + . . . + . . .
                + . . . + . . . + . 4 . + . . .
  )


(defpatch :hats1
  :ses-chat2 . 2.5 . . . 2.5 . . . 2.5 . . . 2.5 . .
  :ses-chat1 + . . . + . 3 . + . . . + . . .
  :do-chat   + . 2 . + . . . + . 2 . + . . .
             + . 2 . + . . . + . 2 . + . 5 .
  )

(defpatch :bg-snares
  :ab-snare3     . 2 . . 2 . . 2
  )
(defpatch :bg-snares2
  :ab-snare1     . . 2 . . 2 . .
  :ab-snare8     . . . . 2 . . 2 . . . 2 . . 1 2
  )

(defpatch :pitch-snares1
  :b2     (repeat 48 nil)
          + . . . + . . . + . . . 2 . 2 .

  :f2     + . . . + . . . + . . . + . . .
          + . . . + . 5 . + . . . + . . .

  :d2     + . . 2 + . . . + . . . + . . .
          + 2 . . + . . . + . . . + . . .
  )

(defpatch :pitch-snares2
  :c#3    + . . . + . . . + . . . + . . .
  :c3     + . . . + . . . + . . . + . . .
  :b2     + . . . + . . . + . . . + . . .
  :a#2    + . . . + . . . + . . . + . . .
  :a2     + . . . + . . . + . . . + . . .
  :g#2    + . . . + . . . + . . . + . . .
          + . . . + . . 3 + . . . + . . .
  :g2     + . . . + . . . + . . . + . . .
  :f#2    + . . . + . . . + . . . + . . .
          + . . . + . . + . . . . + . . .
          + . . . + . . . + . . . + . . .
          + . . . + . . + . 3 . . + . . .
  :f2     + . . . + . . . + . . . + . . .
  :e2     + . . . + . . . + . . . + . . .
  :d#2    + . . . + . . . + . . . + . . .
  :d2     + . . . + . . . + . . . + . . .
  :c#2    + 4 . . + . . . + 4 . . + . . .
  :c2     + . . . + . . . + . . . + . . .
  )
