(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts "defpattern"
  (fact "Handles even length of tracks"
        (do
          (m/defpattern :intro
            :k 1 m/. 2 m/.
            :p 3 m/. 4 m/.)
          (:intro @m/patterns) => {:k {0 1, 2 2}
                                   :p {0 3, 2 4}}))

  (fact "Handles different lenght of tracks"
        (do
          (m/defpattern
            :k 1 m/. 2
            :p 3 m/. 4 m/.)
          (:intro @m/patterns) => {:k {0 1 2 2}
                                   :p {0 3, 2 4}})))
