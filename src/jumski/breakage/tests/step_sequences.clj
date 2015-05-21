(ns jumski.breakage.tests.step-sequences
  (:use [jumski.breakage.tests.protocols :only [StepSequence]])
  (:use [overtone.midi :as midi]))

(comment

  {:time 0
   :pitch 67
   :duration 2
   :part :melody}
)

(defrecord Patch [steps]
  StepSequence
  (notes-for-step [step tname->note]
    (for [[tname steps] (:steps this)
          :when (not (nil?
                       (nth (cycle steps) step)))
          :let [velo (* 12.7 (inc velo))]]
      [anote velo])))

;; (defrecord Pattern [tracks]
;;   StepSequence
;;   (notes-for-step [step]
;;     (for [[ch patch] (:tracks this)
;;           :let [notes (notes-for-step patch step)]]
;;       [ch notes])))

