(ns jumski.breakage.sequencer_2
  (:use [overtone.live :as o]))

(def options {:resolution 1/480})

(defn foo
  [t val]
  (println val)
  (let [next-t (+ t 200)]
    (o/apply-at next-t #'foo [next-t (inc val)])))

;; (foo
;; (defn play [loop mnome]
;;   (let [


