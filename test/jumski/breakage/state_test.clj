(ns jumski.breakage.state-test
  (:require [midje.repl :refer :all]
            [jumski.breakage.state :refer [defpatch make-pattern db]]))

(facts
  "make-pattern"

  (fact "Does not cycle anything if tracks are even in length"
        (make-pattern [:k 1 2 :p 3 4]) => {:k [1 2] :p [3 4]})

  (fact "Cycles shorter tracks to length of longest one"
        (make-pattern [:k 1 2 :p 3 4 5 :h 6 7 8 9])
        => {:k [1 2 1 2] :p [3 4 5 3] :h [6 7 8 9]})

  (fact "Preserves nil steps"
        (make-pattern [:k 1 nil 2 nil]) => {:k [1 nil 2 nil]}))

(facts
  "defpatch"

  (with-state-changes [(before :facts (reset! db {}))]

   (fact "it stores contents and options under given key"
         (defpatch :name {:option :value} :k1 1 nil :p nil 2)
         (:name @db) => {:opts {:option :value}
                         :body [:k1 1 nil :p nil 2]})

   (fact "it uses default options if not provided"
         (defpatch :name :k1 1 nil :p nil 2)
         (:name @db) => {:opts {} :body [:k1 1 nil :p nil 2]})

   (fact "it flattens body so sequences can be passed"
         (defpatch :name {} [:k1 1 nil] [:p nil 2])
         (:name @db) => {:opts {} :body [:k1 1 nil :p nil 2]})

   (fact "it evals lists so you can compose from parts"
         (defpatch :name {} (take 4 (cycle [1 2 3])))
         (:name @db) => {:opts {} :body [1 2 3 1]})
    ))

;; (facts
;;   "defpatch"
;;
;;   (with-state-changes [(before :facts (reset! db {}))]
;;     (fact "stores pattern created by make-pattern in an atom"
;;           (defpatch :name :k 1 2 3 4)
;;           (:name @db) => {:k [1 2 3 4]})
;;
;;     (fact "Anything other is treated as nil step"
;;           (defpatch :name :k 1 / + . 1 | = - 1 "" \c #{})
;;           (:name @db) => {:k [1 nil nil nil 1 nil nil nil 1 nil nil nil]})))
;;
