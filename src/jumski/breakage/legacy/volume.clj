(ns jumski.breakage.tests.volume
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           ;; :chat1  [_ 1 _ 2 _ 3 _ 4]
           ;; :chat1  [5 _ 6 _ 7 _ 8 _]

           :rroll  [_ _ _ _ _ _ _ _ _ _ _ _ _ _ 3 2]
           :kick4  [8 _ _ 8 _ _ 8 _]
           ;; :chat2  [_ 3 _ _ 3 _ _ _]
           :snare1 [_ _ 1 _ _ 1 _ _]
                   [_ _ 2 _ _ 3 _ _]
           :snare2 [_ _ _ _ _ _ _ _
                    _ _ _ 3 _ _ _ _
                    _ _ _ _ _ _ _ _
                    _ _ _ 3 _ _  2 _]
                   [_ _ _ _ _ _ -7 _]
           :snare3 [_ 2 _ _  6 _  _ 2]
                   [_ 2 _ _ -5 _  _ 2]
           :chat3  [7] [-7 -5 1]
])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 136)
