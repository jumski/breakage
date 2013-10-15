(ns jumski.breakage.breaks.pitches_1
  (:use ;[jumski.breakage.step-seq]
        [jumski.breakage.pattern])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [:kick1 [7 _ _ _ 7 _ _ _ 7 _ _ _ 7 _ _ _]
                  [1 _ _ _ 2 _ _ _ 3 _ _ _ 4 _ _ _]
           :snare [7 _ _ _ 7 _ _ _ 7 _ _ _ 7 _ _ _]])

(def s (split-on-keyword base))
(def n (map normalize-track s))
;;
;; (defn pattern [] (merge-with concat base))
;;
;; (o/stop)
;; (play amen-break #'pattern 194)
