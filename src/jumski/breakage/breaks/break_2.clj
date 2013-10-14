(ns jumski.breakage.breaks.break_2
  (:use [jumski.breakage.step-seq])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(defn inc-vol [vol trk]
  (map (fn [q]
         (if (not= q _)
           (+ q vol)
           q))
       trk))


(def amen-break (kit/load-kit "samples/amen-break"))

(def break-1 {:kick2   [7 _ _ 7 _ _ 7 _ _ _ _ _ _ _ _ _]
              :stab    [_ _ _ _ _ _ _ _ _ _ _ 3 _ _ _ _]
              :crash   [_ _ _ _ _ _ _ _ 5 _ _ _ _ _ _ _]
              :chat2   [_ 4 _ _ _ _ _ _ _ _ 5 _ 5 _ 5 _]
              :ssnare1 [_ _ 7 _ 5 7 _ 5 _ 5 _ 7 _ 7 _ 5]})

(def break-2 (assoc break-1
                    :stab    [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
                    :ssnare1 [_ _ 9 _ 7 9 _ 7 _ 7 _ 9 _ 9 _ 7]))

(defn pattern [] (merge-with concat break-1 break-2))

(o/stop)
(play amen-break #'pattern 140)
