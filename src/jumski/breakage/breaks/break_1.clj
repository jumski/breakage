(ns jumski.breakage.breaks.break_1
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def dnb [:kick1     [7 _ _ _ _ _ _ 7 _ _ 7 _ _ _ _ _]
          :snare2    [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
          :chat2     [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
          :ssnare3   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]])

(def dnb  [:kick1    [7 _ _ _ _ _ _ _ _ _ 7 _ _ _ _ _]
           :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
           :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
           :ssnare3  [_ _ _ _ _ _ _ _ _ 6 _ _ _ _ _ _]])

(def dnb  [:kick1    [7 _ 7 _ _ _ _ _ _ _ 7 _ _ _ _ _]
           :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
           :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
           :ssnare3  [_ _ _ _ _ _ _ 6 _ _ _ _ _ _ _ _]])

(def dnb  [:kick1    [7 _ 7 _ _ _ _ _ _ _ 7 _ _ _ _ _]
           :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
           :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
           :ssnare3  [_ 3 _ 2 _ 2 _ 6 _ 6 _ 2 _ 4 _ 3]])

(def dnb  [:kick1   [7 _ 7 _ _ _ _ _ 8 _ _ _ _ _ _ _]
           :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
           :chat2   [_ _ 3 _ _ _ 4 _ _ _ 4 _ 4 _ 4 _]
           :ssnare3 [_ _ _ _ _ _ _ 4 _ 4 _ 4 _ 4 _ 4]
                    [_ _ _ _ _ _ _ 1 _ 1 _ 1 _ 2 _ 1]])

(defn pattern [] dnb)

(o/stop)
(play amen-break #'pattern 154)
