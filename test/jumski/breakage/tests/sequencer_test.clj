(ns jumski.breakage.tests.sequencer-test)
(use 'clojure.test)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.tests.sequencer :as s] :reload)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts "Sequencer"
  (facts "velo-for-step"
    (s/velo-for-step [0 1 2 3] 0) => (* 1 12.7)
    (s/velo-for-step [0 1 2 3] 1) => (* 2 12.7)
    (s/velo-for-step [0 1 2 3] 2) => (* 3 12.7)
    (s/velo-for-step [0 1 2 3] 3) => (* 4 12.7)

    (s/velo-for-step [0 1 2 3] 4) => (* 1 12.7)
    (s/velo-for-step [0 1 2 3] 5) => (* 2 12.7)
    (s/velo-for-step [0 1 2 3] 6) => (* 3 12.7)
    (s/velo-for-step [0 1 2 3] 7) => (* 4 12.7)
    ))
