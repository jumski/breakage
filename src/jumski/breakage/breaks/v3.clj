(ns jumski.breakage.breaks.v3
  (:use [jumski.breakage.sequencer] :reload)
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :snare3 (->> [2 _ _] cycle (take 16))
                   (->> 10 (iterate #(* 0.9 %)) (take 16))
           :kick3   [3 _ _ 3 _ _ _ _]
           :ssnare2 [_ _ _ _ _ _ 6 _]
           ;; :snare1  [_ _ _ 3 2 _ 2   _ _ 2 _ 3 2 _ 2   _]
           ;;          [_ _ _ _ 2 _ 0.6 _ 5 _ _ _ 2 _ 0.6 _]
           :chat2   [1 _ _]
                    (->> 1 (iterate #(* 0.9 %)) (take 8))
           ;; :chat2   [_ 1.5]
           ;; :ohat    [_ _ 1 _]
           ;; :ssnare2 [_ _ _ _ _ 2 _ _]
           ;; :ssnare3 [_ 2 _ _ 2 2 _ _]
           ;;          [_ 7 _ _ _ 2 _ _]
           ;; :rsnare  [_ _ 2 _ _ _ _ _ _ _ _ _ _ _ _ _]
           ;; :crash   (concat
           ;;            (take 16 (repeat _))
           ;;          [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _])
           ])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 120)
