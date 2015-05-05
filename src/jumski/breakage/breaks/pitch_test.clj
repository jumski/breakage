(ns jumski.breakage.breaks.pitch_test
  (:use [jumski.breakage.sequencer])
  (:require [jumski.breakage.kit :as kit])
  (:require [overtone.live :as o]))

(def amen-break (kit/load-kit "samples/amen-break"))

(def base [
           :kick4  [4 _ 1 _ 1 _ 4 _ 1 _ 1 _ 4 _ 1 _]
           :chat1  [_ _ 2 _ 2 _ _ _ 2 _ 2 _ _ _ 2 _]
           :snare1 [_ _ _ 1 _ 1 _ _ _ 1 _ 1 _ _ _ 1]
                   [_ _ _ 1 _ 2 _ _ _ 1 _ 2 _ _ _ 1]
           :stab   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ _ _ _ _ _ _ _ 2 _ _ _ _ _]
           :snare2 [_ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ 1 _ _ 1 _ _ 1 _ _ _ 1 _ _
                    _ _ _ 1 _ _ _ _ _ _ _ _ _ _ _ _
                    _ _ _ 1 _ _ 1 1 _ 1 _ _ 1 _ _ 1]
  ])

(def base [
           ;; :snare1 [1 1 1 1 1 1 1 1 1 1 1  1  1  1  1  1]
           ;;         [_ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15]
           ;; :snare1 [1  1  1  1  1  1  1 1 1 1 1 1 1 1 1 1]
           ;;         [15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 _]
           :kick4  (take 16 (cycle [4 _ _]))
           :snare2 [_ _ _ _ _ _ _ _ _ _ _ _ 4 _ _ _]
           :chat2  [2 _ 2 _ 2 _ 2 _]
           :snare1 [_ 1 _ 1 _ 1 _ _]
                   [_ 1 _ 2 _ 1 _ _]
           ;; :stab   [_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
           ;;          _ _ _ _ _ _ _ _ _ _ _ 2 _ _ _ _]
           ])

(defn pattern [] base)

(o/stop)
(o/volume 0.5)
(play amen-break #'pattern 154)
