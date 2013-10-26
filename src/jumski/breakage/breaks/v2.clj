(ns jumski.breakage.breaks.v2
  (:use [jumski.breakage.sequencer] :reload)
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           ;; :kick1   [5 _ _ _ _ _ _ _]
           ;; :snare1  [_ _ _ 3 2 _ 3   _ _ 2 _ 3 2 _ 3   _]
           ;;          [_ _ _ _ 2 _ 0.6 _ 5 _ _ _ 2 _ 0.6 _]
           :chat1   [3 _]
           :chat2   [1]
           ;; :ssnare2 [_ _ _ 4]
           ;; :ssnare3 [_ 2 _ _ 4 2 _ _]
           ;;          [_ 7 _ _ _ 2 _ _]
           ])

(defn pattern [] base)

(o/stop)
(play amen-break #'pattern 120)
