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

    (fact "flattens any sequences"
      (m/make-pattern [:k 1 [2 3] 4 :p [5 6] [7 8]]) => {:k [1 2 3 4]
                                                         :p [5 6 7 8]})


    (fact "Preserves nil steps"
      (m/make-pattern [:k 1 nil 2 nil]) => {:k [1 nil 2 nil]}))

  (facts "defpatch"
    (fact "stores pattern created by make-pattern in an atom"
          (do
            (m/defpatch :intro :k 1 2 3 4)
            (m/getpattern :intro)) => {:k [1 2 3 4]})

    (fact "Anything other is treated as nil step"
          (do
            (m/defpatch :intro :k 1 / + . 1 | = - 1 "" \c #{})
            (m/getpattern :intro)) => {:k [1 nil nil nil 1 nil nil nil 1 nil nil nil]}))

  (facts "tracks-to-play"
    (def patt {:k [  1 nil 2 nil]
               :p [nil nil 3 4  ]})
    (m/tracks-to-play patt 0) => {:k 1}
    (m/tracks-to-play patt 1) => {}
    (m/tracks-to-play patt 2) => {:k 2 :p 3}
    (m/tracks-to-play patt 3) => {:p 4})

  )
