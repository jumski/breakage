(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts "make-pattern"
  (fact "Handles even length of tracks"
    (m/make-pattern [:k 1 2 :p 3 4])
      => {:k {0 1, 1 2} :p {0 3, 1 4}})

  (fact "Handles different lenght of tracks"
    (m/make-pattern [:k 1 2 :p 3 4 5])
      => {:k {0 1, 1 2} :p {0 3, 1 4, 2 5}})

  (fact "Skips nil steps"
    (m/make-pattern [:k 1 nil 2 nil]) => {:k {0 1, 2 2}}))

(facts "defpattern"
  (fact "Stores pattern created by make-pattern in an atom"
    (do
      (m/defpattern :intro :k 1 2 3 4)
      (:intro @m/patterns)) => {:k {0 1, 1 2, 2 3, 3 4}})

  (fact "Anything other than integer is treated as nil step"
    (do
      (m/defpattern :intro :k 1 / + . 1 | = - 1 "" \c #{})
      (:intro @m/patterns)) => {:k {0 1, 4 1, 8 1}})

  )
