(ns jumski.breakage.mindstorm_test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.mindstorm :as m] :reload)

(facts
  "defpattern"

  (fact
    "Handles even length of tracks"
    (do
      (m/defpattern :intro :k 1 2 :p 3 4)
      (:intro @m/patterns) => {:k {0 1, 1 2}
                               :p {0 3, 1 4}}))

  (fact
    "Handles different lenght of tracks"
    (do
      (m/defpattern :intro :k 1 2 :p 3 4 5)
      (:intro @m/patterns) => {:k {0 1, 1 2}
                               :p {0 3, 1 4, 2 5}}))

  (fact
    "Skips nil steps"
    (do
      (m/defpattern :intro :k 1 nil 2 nil)
      (:intro @m/patterns)) => {:k {0 1, 2 2}})

  (fact
    "Anything other than integer is treated as nil step"
    (do
      (m/defpattern :intro :k 1 / + . 1 | = -)
      (:intro @m/patterns)) => {:k {0 1, 4 1}}))
