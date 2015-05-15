(ns jumski.breakage.tests.break-1
  (:use [jumski.breakage.tests.sequencer :as s])
  (:use [jumski.breakage.mindstorm :only [defpattern patterns]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai]))

(comment
  (restart-sequencing :intro 154 akai-player)
  (stop-sequencing :intro)
  )

; SOURCE

(def midi-player (akai/make-player "USB"))

(defpattern :intro
  :kick1  9 . . 9 9 . . .
  :snare3 . . . . 5
  :chat1  2 . 4 . . 3
  :chat3  . . . . 4 . . .
  :kick6
          4 4 4 4 4 . . .
          . . . . . . . .
          . . . . . . . .
          . . . . . . . .
  )

(defpattern :intro2
  :kick1  9 . . 9 9 . . .
  :kick1  9 . 3 9 . . 9 .

  :snare1 . . . . . 9 . .
          . . . . . 1 . .
  :snare3 . . . . 5
  :snare1 . . 7
  :snare1 . 2 . 3 . 7 . .
          . 2 . 3 . 7 . .
          . 2 . 3 . 7 . .
          . . 5 . . 7 . .
  :chat1  2 . 4 . . 3
  :chat3  . . . . 4 . . .
  ;; :chat3  . 2 .
  ;; :chat1  . 4
  :kick6
          4 4 4 4 4 . . .
          . . . . . . . .
          . . . . . . . .
          . . . . . . . .
  ;; :chat1 3 2 1
  ;; :csnare . 3 . 3
  )
