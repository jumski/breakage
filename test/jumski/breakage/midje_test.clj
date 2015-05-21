(ns jumski.breakage.midje-test
  (:require [midje.repl :refer :all]))

(def a (atom 0))

(facts "works"
       (with-state-changes [(before :facts (reset! a 0))]
         (fact "1"
               (swap! a inc)
               @a => 1)

         (fact "2"
               (swap! a dec)
               @a => -1)

         (fact "3"
               (reset! a 7)
               @a => 7)))
