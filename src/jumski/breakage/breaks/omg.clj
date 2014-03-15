(ns jumski.breakage.breaks.omg
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base {:kick1   [5 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _]
  ])
(defn pattern [] base)

(o/stop)
(o/volume 0.3)
(play amen-break #'pattern 178)
