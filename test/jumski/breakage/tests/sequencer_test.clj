(ns jumski.breakage.tests.sequencer)
(use 'clojure.test)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.tests.sequencer :as s] :reload)
(require '[jumski.breakage.tests.mindstorm :as m] :reload)

(facts "Sequencer"
  (facts "steps-to-play"
    (def patt (m/make-pattern [:k 1 nil 2 nil
                               :p 3 4   5 nil])
    (def notemap {:k 1 :p 2})

    (notes-for-step patt 0 notemap) => {1 1}
    (notes-for-step patt 1 notemap) => {2 4}
    (notes-for-step patt 2 notemap) => {1 2, 2 5}
    (notes-for-step patt 3 notemap) => {}

    (notes-for-step patt 0 notemap) => {1 1}
    (notes-for-step patt 1 notemap) => {2 4}
    (notes-for-step patt 2 notemap) => {1 2, 2 5}
    (notes-for-step patt 3 notemap) => {}
