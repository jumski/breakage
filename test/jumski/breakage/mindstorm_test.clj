(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts "Mindstorm"

  (facts "make-pattern"
    (fact "Does not cycle anything if tracks are even in length"
      (m/make-pattern [:k 1 2 :p 3 4])
        => {:k [1 2] :p [3 4]})

    (fact "Cycles shorter tracks to length of longest one"
      (m/make-pattern [:k 1 2 :p 3 4 5 :h 6 7 8 9])
        => {:k [1 2 1 2] :p [3 4 5 3] :h [6 7 8 9]})

    (fact "Preserves nil steps"
      (m/make-pattern [:k 1 nil 2 nil]) => {:k [1 nil 2 nil]}))

  (facts "defpattern"
    (fact "Stores pattern created by make-pattern in an atom"
          (do
            (m/defpattern :intro :k 1 2 3 4)
            (m/getpattern :intro)) => {:k [1 2 3 4]})

    (fact "Anything other than integer is treated as nil step"
          (do
            (m/defpattern :intro :k 1 / + . 1 | = - 1 "" \c #{})
            (m/getpattern :intro)) => {:k [1 nil nil nil 1 nil nil nil 1 nil nil nil]})))
