(ns jumski.breakage.pattern-test
  (:use midje.sweet)
  (:require [jumski.breakage.pattern :as p]))

(facts "about `normalize`"
  (fact "returns as is if smp, vol and pit present"
    (p/normalize [:kick [1 2] [3 4]]) => [:kick [1 2] [3 4]])

  (fact "appends blank pitches if missing"
    (p/normalize [:kick [1 2]]) => [:kick [1 2] [p/_ p/_]])

  (fact "appends missing pitches if count not match"
    (p/normalize [:kick [1 2] [3]]) => [:kick [1 2] [3 p/_]])
)
