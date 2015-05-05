(ns jumski.breakage.step
  (:use [clojure.math.numeric-tower :only [expt]]))

(defmulti volume class)
(defmethod volume :default [step]
  (/ (:v step) 10))

(defn- semitones-to-rate
  "Given transpose in semitones, returns sample rate"
  [semitones]
  (expt 2 (/ semitones 12)) )

(defmulti rate class)
(defmethod rate :default [step]
  (let [semitones (:p step)]
    (cond (or (nil? semitones) (zero? semitones)) 1
          :else (semitones-to-rate semitones))))


