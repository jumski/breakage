(ns jumski.breakage.pattern-test
  (:use midje.sweet)
  (:require [jumski.breakage.pattern :as p]))

(fact p/_ => nil)

(facts "about `split-on-keyword`"
  (let [patt [:k 1 2 :v 3]]
    (fact "split in proper place"
      (p/split-on-keyword patt) => [[:k 1 2] [:v 3]])))

(facts "about `normalize`"
  (fact "returns as is if smp, vol and pit present"
    (p/normalize [:kick [1 2] [3 4]]) => {:kick [[1 2] [3 4]]})

  (fact "appends blank pitches if missing"
    (p/normalize [:kick [1 2]]) => {:kick [[1 2] [p/_ p/_]]})

  (fact "appends missing pitches if count not match"
    (p/normalize [:kick [1 2] [3]]) => {:kick [[1 2] [3 p/_]]}))

;; (facts "about `slice-for-beat`"
;;   (let [patt [[:kick (vec (range 0 16))]
;;               [:hat  (vec (range 20 16))]]]
;;     (facts
;;       (p/track-slice 0 patt :kick) => [0 1 2 3]
;;       (p/track-slice 1 patt :kick) => [4 5 6 7]
;;       (p/track-slice 2 patt :kick) => [8 9 10 11]
;;       (p/track-slice 3 patt :kick) => [12 13 14 15]
;;
;;       (p/track-slice 16 patt :kick) => [0 1 2 3]
;;       (p/track-slice 17 patt :kick) => [4 5 6 7]
;;       (p/track-slice 18 patt :kick) => [8 9 10 11]
;;       (p/track-slice 19 patt :kick) => [12 13 14 15])))
