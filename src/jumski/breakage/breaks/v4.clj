(ns jumski.breakage.breaks.v4
  (:use [jumski.breakage.sequencer] :reload)
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           ;; :kick1  [6 _ _ _ _ _ 6 _ _ _ _ _ 6 _ _ _]
           ;; :chat2  [_ _ 5 _ 5 _ _ _ 5 _ 5 _ _ _ 5 _]
           ;;         [_ _ 2 _ 2 _ _ _ 2 _ 2 _ _ _ 2 _]
           :csnare [_ _ _ _ _ _ _ _ _ 1 _ 1 _ _ _ _]
           :snare3 [_ _ 3 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 3 _ _ _ _ _ _ _]
           :snare4 [_ _ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 1 _ _ _ _ _]
           :stab   (->> [1 1 1] cycle (take 16))
                   (let [up (->> 5 (iterate #(* 1.33 %)) (take 8))
                         down (->> 3 (iterate #(* 0.66 %)) (take 8))]
                    (interleave up down)
                     )
           ])

(defn pattern [] base)

(o/stop)
(o/volume 0.8)
(play amen-break #'pattern 178)
