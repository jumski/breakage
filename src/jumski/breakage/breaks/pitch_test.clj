(ns jumski.breakage.breaks.pitch_test
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :kick4  [4 _ 1 _ 1 _ 4 _ 1 _ 1 _ 4 _ 1 _]
           :chat1  [_ _ 2 _ 2 _ _ _ 2 _ 2 _ _ _ 2 _]
           :snare1 [_ _ _ 1 _ 1 _ _ _ 1 _ 1 _ _ _ 1]
                   [_ _ _ 2 _ 3 _ _ _ 2 _ 3 _ _ _ 2]
           :stab   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ _ _ _ _ _ _ _ 2 _ _ _ _ _]
           :snare2 [_ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ 1 _ _ 1 1 _ 1 _ _ 1 _ _ 1]
  ])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 154)
