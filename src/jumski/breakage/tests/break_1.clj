(ns jumski.breakage.tests.break-1
  (:use [jumski.breakage.tests.sequencer :as s])
  (:use [jumski.breakage.mindstorm :only [defpatch patterns]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai]))

(comment
  (s/restart-sequencing :intro 154 ch0)
  (s/stop-sequencing :intro)
  )

(def ch0 (akai/make-player "USB" 0))
(def ch1 (akai/make-player "USB" 1))

(defpatch :intro
  :kick1 8 .)

(defpatch :intro
  :kick5     6 . 3 . 8 . 4 .
  :snare1    . . . . 5 . . .   . . . . . . . .
  :snare7    . . . . . . . .   . 9 . . 9 . . 9
             . . . . . . . .   . 9 . . . . . .
  :snare8    . 9 . . 9 . . 9   . . . . . . . .
             . 9 . . . . . .   . . . . . . . .
             ;; . . . . . . . .
             ;; . 9 . . . . . .
             ;; . . . . . . . .
             ;; . 9 . . . . . .
  )

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
  ;; :chat2  . 4
  :kick6
          4 4 4 4 4 . . .
          . . . . . . . .
          . . . . . . . .
          . . . . . . . .
  ;; :chat1 3 2 1
  ;; :csnare . 3 . 3
  )
