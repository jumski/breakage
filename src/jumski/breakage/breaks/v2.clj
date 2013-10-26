(ns jumski.breakage.breaks.v2
  (:use [jumski.breakage.sequencer] :reload)
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [:kick1   [5 _ _ _ _ _ _ _]
           :snare1  [_ _ 3 _ 2 _ 3 _]
                    [_ _ _ _ 2 _ 0.6 _]])

(defn pattern [] base)

(o/stop)
(play amen-break #'pattern 194)
