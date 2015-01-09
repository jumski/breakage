(ns jumski.breakage.breaks.testgu
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :skick    [5 _ _ _ _ _ _ _ _ _ 5 _ _ _ _ _]
           :snare1   [_ _ _ _ 3 _ _ _ _ _ _ _ 3 _ _ _]
           :snare2   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
           :chat2    [_ 1 _ 1 _ 1 _ 1 _ 2 2 2 2 2 2 2]
                     [_ 3 _ 2 _ 2 _ 2 _ 1 2 3 4 5 6 7]
           :chat3    [_ _ _ _ _ _ 3 _ 3 _ _ _ _ _ _ _
                      _ _ _ _ _ _ 3 _ 3 _ _ _ _ _ _ _]
           :ssnare2  [_ _ _ _ _ _ _ 5 _ 5 _ _ _ _ _ _
                      _ _ _ _ _ _ _ 5 _ 5 _ _ _ _ _ _
                      _ _ _ _ _ _ _ 5 _ 5 _ _ _ _ _ _
                      _ _ _ _ _ _ _ 5 _ 5 _ _ _ _ 5 _]
           ])

(defn pattern [] base)

(o/stop)
(play amen-break #'pattern 154)
