(ns jumski.breakage.step-test)
(use 'midje.repl)
(require '[jumski.breakage.step :as s] :reload)
(require '[clojure.math.numeric-tower :as m])

(fact "volume returns velocity divided by 10"
  (s/volume {:v 5}) => 1/2)

(facts
  (s/rate {:p nil}) => 1
  (s/rate {:p 0})   => 1
  (s/rate {:p 1})   => 1

  (s/rate {:p 12}) => 2
  (s/rate {:p 24}) => 4
  (s/rate {:p 36}) => 8
  (s/rate {:p 5})  => (m/expt 2 5/12)
  (s/rate {:p 7})  => (m/expt 2 7/12)

  (s/rate {:p -12}) => 1/2
  (s/rate {:p -24}) => 1/4
  (s/rate {:p -36}) => 1/8
  (s/rate {:p -5})  => (m/expt 2 -5/12)
  (s/rate {:p -7})  => (m/expt 2 -7/12)
)
