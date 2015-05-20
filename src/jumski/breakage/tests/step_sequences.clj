(ns jumski.breakage.tests.step-sequences
  (:use [jumski.breakage.tests.protocols :only [StepSequence]])
  (:use [overtone.midi :only [note]]))

;; (comment
;;   {:time 0
;;    :pitch 67
;;    :duration 2
;;    :part :melody}
;; )

(defrecord Patch [steps]
  StepSequence
  (notes-for-step [step tname->note]
    (for [[tname steps] (:steps this)
          :let [velo (nth (cycle steps) step)]
          :when (not (nil? velo))
          :let [velo (* 12.7 (inc velo))]]
      [anote velo]))
  (notes-for-step [step]
    (notes-for-step step note)))

(defrecord Pattern [tracks]
  StepSequence
  (notes-for-step [step]
    (for [[ch patch] (:tracks this)
          :let [notes (notes-for-step patch step)]]
      [ch notes])))


(defrecord Chain [parts])
