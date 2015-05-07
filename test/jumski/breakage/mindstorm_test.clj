(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(fact "Handles even length of tracks"
  (m/make-pattern :k 1 m/. 2 m/.
                  :p 3 m/. 4 m/.) => {:k {0 1, 2 2}
                                      :p {0 3, 2 4}})

(fact "Handles different lenght of tracks"
  (m/make-pattern :k 1 m/. 2
                  :p 3 m/. 4 m/.) => {:k {0 1 2 2}
                                      :p {0 3, 2 4}})
