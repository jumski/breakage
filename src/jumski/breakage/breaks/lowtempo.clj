(ns jumski.breakage.breaks.lowtempo
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :skick    [5 _ _ _ _ _ _ _ 5 _ _ _ _ _ _ _]
                     [3 _ _ _ _ _ _ _ 3 _ _ _ _ _ _ _]
           :chat1    (take 16 (cycle [2 _ ]))
           :snare1   [_ _ _ _ 3 _ _ _ _ _ _ _ 3 _ _ _
                      _ _ _ _ 3 _ _ _ _ _ _ _ 3 _ 3 3]
                     [_ _ _ _ 3 _ _ _ _ _ _ _ 3 _ _ _
                      _ _ _ _ 3 _ _ _ _ _ _ _ 3 _ 5 7]
           ;; :snare2   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
           ;;            _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ _
           ;;            _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
           ;;            _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
           :chat2    [_ 1 _ 1 _ 1 _ 1 _ 2 2 2 2 2 2 2]
                     [_ 3 _ 2 _ 2 _ 2 _ 1 2 3 4 5 6 7]
           :chat1    [_ _ _ _ _ _ 1 _ 1 _ _ _ _ _ _ _]
           :ssnare2  [_ _ _ _ _ _ _ 5 _ 5 _ _ _ _ _ _]
                     [_ _ _ _ _ _ _ 8 _ 8 _ _ _ _ _ _]
           ])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 76)
