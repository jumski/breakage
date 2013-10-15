(ns jumski.breakage.breaks.pitches_1
  (:use [jumski.breakage.step-seq]
        [jumski.breakage.pattern])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [:kick1  [7 _ _ _ 7 _ _ _ _ _ 7 _ _ _ _ _]
           :snare1 [_ _ _ _ _ _ 5 _ _ _ _ _ 6 _ _ _]
                   [_ _ _ _ _ _ 1 _ _ _ _ _ 2 _ _ _]])

(defn pattern [] base)

(o/stop)
(play amen-break #'pattern 194)
;; (play-pattern amen-break pattern :snare1 0)
