(ns jumski.breakage.breaks.pitches_1
  (:use [jumski.breakage.step-seq]
        [jumski.breakage.pattern])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base3 [:kick1  [7 _ _ _ 7 _ _ _ _ _ 7 _ _ _ _ _]
           :snare1 [_ _ _ _ _ _ 5 _ _ _ _ _ 6 _ _ _]
                   [_ _ _ _ _ _ 1 _ _ _ _ _ 2 _ _ _]])
(def base3 [:kick2  [7 _ _ _ 7 _ _ _ _ _ 7 _ _ _ _ _]
            :snare1 [_ _ _ 2 _ _ 5 _ _ _ _ _ 6 0 4 3]
                    [_ _ _ 2 _ _ 1 _ _ _ _ _ 2 2.1 2.2 2.3]
            :chat2   [_ _ 4 _ _ 4 _ _ 4 _ _ 4 _ _ 4 _]])

                     ;[X _ _ _ X _ _ _ X _ _ _ X _ _ _]
(def base3 [:kick1    [7 _ _ _ _ _ 7 _ _ _ _ _ _ _ _ _]
                      [_ _ _ _ _ _ _ _ _ _ _ _ 3 _ 2 _]

            :chat2    [_ _ 5 _ 5 _ _ _ 5 _ 5 _ _ _ _ _]

            :ssnare1  [_ _ _ 5 _ 3 _ _ _ 5 _ 3 _ _ _ _]
                      [_ _ _ 1 _ 2 _ _ _ 1 _ 2 _ _ _ _]

            :snare4   [_ _ _ _ _ _ _ _ _ _ _ _ 5 _ 3 _]
                      [_ _ _ _ _ _ _ _ _ _ _ _ 1 _ 1 _]
            ])

(defn pattern [] base3)

(o/stop)
(play amen-break #'pattern 194)
;; (play-pattern amen-break pattern :snare1 0)
