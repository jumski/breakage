(ns jumski.breakage.break_1
  (:use [jumski.breakage.step-seq])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def dnb-base {:kick1     [7 _ _ _ _ _ _ 7 _ _ 7 _ _ _ _ _]
               :snare2    [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
               :chat2     [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
               :ssnare3   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]})

(def dnb-base2 {:kick1    [7 _ _ _ _ _ _ _ _ _ 7 _ _ _ _ _]
                :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :ssnare3  [_ _ _ _ _ _ _ _ _ 6 _ _ _ _ _ _]})

(def dnb-final {:kick1    [7 _ 7 _ _ _ _ _ _ _ 7 _ _ _ _ _]
                :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :ssnare3  [_ _ _ _ _ _ _ 6 _ _ _ _ _ _ _ _]})

(def dnb-final {:kick1    [7 _ 7 _ _ _ _ _ _ _ 7 _ _ _ _ _]
                :snare2   [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                :chat2    [_ _ 3 _ _ _ 4 _ 4 _ _ _ _ _ 3 _]
                :ssnare3  [_ _ _ _ _ _ _ 6 _ 6 _ _ _ _ _ _]})

(def dnb-final2 {:kick1   [7 _ 7 _ _ _ _ _ 8 _ _ _ _ _ _ _]
                 :snare2  [_ _ _ _ 7 _ _ _ _ _ _ _ 7 _ _ _]
                 :chat2   [_ _ 3 _ _ _ 4 _ _ _ 4 _ 4 _ 4 _]
                 :ssnare3 [_ _ _ _ _ _ _ 6 _ 6 _ 6 _ 6 _ 6]})

(defn pattern [] (merge-with concat
                         dnb-base dnb-base2 dnb-base dnb-final
                         dnb-base dnb-base2 dnb-base dnb-final2))

(o/stop)
(play amen-break #'pattern 194)
