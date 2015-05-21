(ns jumski.breakage.breaks.testy
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :kick1   [5 _ _ _ ]
  ])

(defn pattern [] (merge-with concat base))

(o/stop)
(o/volume 0.6)
(play amen-break #'pattern 194)
