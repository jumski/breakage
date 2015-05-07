(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(m/defpattern intro :k 1 _ 2 _)
                    ;; :p _ 3 _ 4)

(facts
  (:intro @m/patterns) => {:k {0 1, 2 2}})
                           ;; :p {1 3, 3 4}})
