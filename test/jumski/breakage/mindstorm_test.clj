(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts "make-pattern"
  (fact "Handles even length of tracks"
    (m/make-pattern [:k 1 m/. 2 m/.
                    :p 3 m/. 4 m/.]) => {:k {0 1, 2 2}
                                        :p {0 3, 2 4}})

  (fact "Handles different lenght of tracks"
    (m/make-pattern [:k 1 m/. 2
                    :p 3 m/. 4 m/.]) => {:k {0 1 2 2}
                                          :p {0 3, 2 4}}))

(facts "defpattern"
  (fact "parses stuff using make-pattern and stores in an atom"
    (do
      (m/defpattern intro :k 1 m/. 2 m/.)
      (:intro @m/patterns)) => {:k {0 1, 2 2}}))
