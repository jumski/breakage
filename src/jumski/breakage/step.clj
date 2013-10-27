(ns jumski.breakage.step)

(defmulti volume class)
(defmethod volume :default [step]
  (/ (:v step) 10))

(defmulti rate class)
(defmethod rate :default [step]
  (let [p (:p step)]
    (if (nil? p) 1 p)))


