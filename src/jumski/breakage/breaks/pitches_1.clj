(ns jumski.breakage.breaks.pitches_1
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base3 [
  :kick4    [5 _ _ 2 _ _ 5 _ _ 2 _ _ 3 _ _ _]
            [1 _ _ 6 _ _ 1 _ _ 4 _ _ 2 _ _ _]
  :chat1    [_ _ 4 _ 4 _ 4 _ 4 _ 4 _ _ _ _ _]

  :csnare   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ 3 _ _ _ _ _]

  :stab     [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
             _ _ _ _ _ _ _ _ _ _ 3 _ _ _ _ _]

  :ssnare1  [_ _ _ 4 _ 2 _ 2 _ 4 _ 2 _ _ 6 _]
            [_ _ _ 1 _ 2 _ 2 _ 1 _ 2 _ _ _ _]

  :snare4   [_ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ 2 1 _ 1
             _ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _]
            [_ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _
             _ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _]
            ])

(defn pattern [] base3)

(o/stop)
(o/volume 0.2)
(play amen-break #'pattern 184)
