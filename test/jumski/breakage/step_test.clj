(ns jumski.breakage.step-test)
(use 'midje.repl)
(require '[jumski.breakage.step :as s] :reload)

(fact "volume returns velocity divided by 10"
  (s/volume {:v 5}) => 1/2)

(facts
  (s/rate {:p nil}) => 1
  (s/rate {:p 1})   => 1
  (s/rate {:p 2})   => 2)
