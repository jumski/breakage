(ns jumski.breakage.pattern-test
  (:use midje.sweet)
  (:require [jumski.breakage.pattern :as p]))

(fact p/_ => nil)

(facts "about `split-on-keyword`"
       (let [patt [:k 1 2 :v 3]]
         (fact "split in proper place"
               (p/split-on-keyword patt) => [[:k 1 2] [:v 3]])))

(facts "about `normalize-track`"
       (fact "returns as is if smp, vol and pit present"
             (p/normalize-track [:kick [1 2] [3 4]]) => [:kick [[1 2] [3 4]]])

       (fact "appends blank pitches if missing"
             (p/normalize-track [:kick [1 2]]) => [:kick [[1 2] [p/_ p/_]]])

       (fact "appends missing pitches if count not match"
             (p/normalize-track [:kick [1 2] [3]]) => [:kick [[1 2] [3 p/_]]]))

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


(facts "about `slice-for-beat`"
       (let [patt [:kick [[0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15]
                          [15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0]]]]
         (facts
           (p/track-slice 0 patt :kick) => [[0 1 2 3] [15 14 13 12]]
           (p/track-slice 1 patt :kick) => [[4 5 6 7] [11 10 9 8]]
           (p/track-slice 2 patt :kick) => [[8 9 10 11] [7 6 5 4]]
           (p/track-slice 3 patt :kick) => [[12 13 14 15] [3 2 1 0]]

           (p/track-slice 16 patt :kick) => [[0 1 2 3] [15 14 13 12]]
           (p/track-slice 17 patt :kick) => [[4 5 6 7] [11 10 9 8]]
           (p/track-slice 18 patt :kick) => [[8 9 10 11] [7 6 5 4]]
           (p/track-slice 19 patt :kick) => [[12 13 14 15] [3 2 1 0]]
           )))
