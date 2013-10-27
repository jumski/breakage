(ns jumski.breakage.breaks.break_2
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(defn inc-vol [vol trk]
  (map (fn [q]
         (if (not= q _)
           (+ q vol)
           q))
       trk))


(def amen-break (kit/load-kit "samples/amen-break"))

(def break-1 [:kick2   [7 _ _ 7 _ _ 7 _ _ _ _ _ _ _ _ _]
              :stab    [_ _ _ _ _ _ _ _ _ _ _ 3 _ _ _ _]
              :crash   [_ _ _ _ _ _ _ _ 5 _ _ _ _ _ _ _]
              :chat2   [_ 4 _ _ _ _ _ _ _ _ 5 _ 5 _ 5 _]

              :ssnare1 [_ _ 6 _ 4 6 _ 4 _ 4 _ 6 _ 6 _ 4]
                       [_ _ 0 _ 2 0 _ 2 _ 2 _ 0 _ 0 _ 2]
                       ])

;; (def break-2 (assoc break-1
;;                     :stab    [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
;;                     :ssnare1 [_ _ 9 _ 7 9 _ 7 _ 7 _ 9 _ 9 _ 7]))

(defn pattern [] break-1)

(o/stop)
(play amen-break #'pattern 140)
