(ns jumski.breakage.pattern-test
  (:use midje.sweet)
  (:require [jumski.breakage.pattern :as p]))

(fact p/_ => nil)

(facts "about `split-on-keyword`"
       (let [patt [:k 1 2 :v 3]]
         (p/split-on-keyword patt) => [[:k 1 2] [:v 3]]
         (p/split-on-keyword patt) => vector?
         (p/split-on-keyword patt) => (partial every? vector?)))


(facts "about `normalize-track`"
       (fact "returns as is if smp, vol and pit present"
             (p/normalize-track [:kick [1 2] [3 4]]) => [:kick [[1 2] [3 4]]])

       (fact "appends blank pitches if missing"
             (p/normalize-track [:kick [1 2]]) => [:kick [[1 2] [p/_ p/_]]])

       (fact "appends missing pitches if count not match"
             (p/normalize-track [:kick [1 2] [3]]) => [:kick [[1 2] [3 p/_]]])

       (facts
         (p/normalize-track [:kick [1 2] [3]]) => vector?
         (p/normalize-track [:kick [1 2] [3]]) => #(->> % first keyword?)
         (p/normalize-track [:kick [1 2] [3]]) => #(->> % last vector?)

         ))

(facts "regression"
      (let [patt '(:kick1
                   [7 0 0 0 7 0 0 0 7 0 0 0 7 0 0 0]
                   [0 0 0 0 2 0 0 0 3 0 0 0 4 0 0 0])]
        (p/normalize-track patt) =>
        [:kick1 [[7 0 0 0 7 0 0 0 7 0 0 0 7 0 0 0]
                 [0 0 0 0 2 0 0 0 3 0 0 0 4 0 0 0]]])

      (let [patt '(:snare [7 0 0 0 7 0 0 0 7 0 0 0 7 0 0 0])]
        (p/normalize-track patt) =>
        [:snare [[7 0 0 0 7 0 0 0 7 0 0 0 7 0 0 0]
                 (vec (repeat 16 nil))]]
        ))


(facts "about `track-slice`"
       (let [patt [[:kick  [[0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15]
                            [15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0]]]
                   [:snare [[20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35]
                            [35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20]]]]]
         (p/track-slice 0 patt :kick) => [[0 1 2 3] [15 14 13 12]]
         (p/track-slice 1 patt :kick) => [[4 5 6 7] [11 10 9 8]]
         (p/track-slice 2 patt :kick) => [[8 9 10 11] [7 6 5 4]]
         (p/track-slice 3 patt :kick) => [[12 13 14 15] [3 2 1 0]]

         (p/track-slice 16 patt :kick) => [[0 1 2 3] [15 14 13 12]]
         (p/track-slice 17 patt :kick) => [[4 5 6 7] [11 10 9 8]]
         (p/track-slice 18 patt :kick) => [[8 9 10 11] [7 6 5 4]]
         (p/track-slice 19 patt :kick) => [[12 13 14 15] [3 2 1 0]]
         ))
