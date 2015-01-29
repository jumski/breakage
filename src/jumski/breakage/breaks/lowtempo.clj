(ns jumski.breakage.breaks.lowtempo
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :skick    [5 _ _ _ 3 _ _ 3 _ _ 5 _ 2 _ 2 _ 5 _ _ _ 3 _ _ 3 _ _ 5 _ _ _ 2 _]
                     [1 _ _ _ 3 _ _ 3 _ _ 1 _ 4 _ 5 _ 1 _ _ _ 3 _ _ 3 _ _ 1 _ _ _ 5 _]
           :rroll    (take 16 (cycle [_ _ 1]))
           :chat1    (take 16 (cycle [_ 2 _]))
           :ssnare2  (take 16 (cycle [_ _ 4]))
           :stab     (take 32  (cycle [_ _ _ _ _ 2]))
           :snare1   [_ _ _ _ 3 _ _ _ _ _ _ _ 3 _ _ _
                      _ _ _ _ 3 _ _ _ _ _ _ _ 3 _ 3 3]
                     [_ _ _ _ 3 _ _ _ _ _ _ _ 3 _ _ _
                      _ _ _ _ 3 _ _ _ _ _ _ _ 3 _ 5 7]
           :snare2   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
           :chat2    [_ 1 _ 1 _ 1 _ 1 _ 2 2 2 2 2 2 2]
                     [_ 3 _ 2 _ 2 _ 2 _ 1 2 3 4 5 6 7]
           :chat1    [_ _ _ _ _ _ 1 _ 1 _ _ _ _ _ _ _]
           :ssnare2  [_ _ _ _ _ _ _ 5 _ 5 _ _ _ _ _ _]
                     [_ _ _ _ _ _ _ 8 _ 8 _ _ _ _ _ _]
           :rroll     (->> [2 1 _] cycle (take 16))
                     (let [up (->> 5 (iterate #(* 1.06 %)) (take 8))
                           down (->> 3 (iterate #(* 0.98 %)) (take 8))]
                       (interleave up down)
                       )
           ])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 156)
