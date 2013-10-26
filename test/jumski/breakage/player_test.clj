(ns jumski.breakage.player-test)
(use 'clojure.test)
(use 'conjure.core)
;; (use 'midje.sweet)
(use 'midje.repl)
(require '[jumski.breakage.player :as p] :reload)

(defn mkdummy [len]
  (take len (iterate inc 0)))

(deftest beat-to-play
  (is (= (list 0 1 2 3) (p/beat-to-play 1 (mkdummy 8))))
  (is (= (list 0 1 2 3) (p/beat-to-play 9 (mkdummy 8))))
  (is (= (list 4 5 6 7) (p/beat-to-play 2 (mkdummy 8))))
  (is (= (list 4 5 6 7) (p/beat-to-play 10 (mkdummy 8)))))

(deftest split-on-keyword
  (is (= [[:k 1 2] [:s 3]]
         (p/split-on-keyword [:k 1 2 :s 3]))))

(deftest vels
  (is (= [1 2]
         (p/vels [:k [1 2] [3 4]]))))

(deftest pits
  (deftest returns-pits-when-present
    (is (= [3 4]
           (p/pits [:k [1 2] [3 4]]))))

  (deftest returns-list-with-only-nil-when-missing
    (is (= [nil]
           (p/pits [:k [1 2]])))))

(deftest hit
  (is (= :k (p/hit [:k 1 2]))))

(deftest cycle-to-same-length
  (is (= [[1 2 3 4]
          [9 7 8 9]
          [1 1 1 1]]
         (p/cycle-to-same-length [1 2 3 4] [9 7 8] [1]))))

(deftest force-same-length
  (is (= [[1 2] [1 nil]]
         (p/force-same-length [1 2] [1]))))

;; (deftest make-pattern
;;   (stubbing [p/split-on-keyword [[:k 1] [:s 1 2]]
;;              p/make-track nil
;;              count nil]
;;        ;; (p/make-pattern [:k 1 :s 1 2])
;;        (count [1 2])
;;        (verify-nth-call-args-for 1 count [1 2])
;;        ;; (verify-nth-call-args-for 1 count [:k 1])
;;        ;; (verify-nth-call-args-for 2 p/make-track [:s 1 2])
;;            ))
(deftest make-pattern
  (def args (atom []))
  (def callno (atom 0))
  (def rets [{:k [1 2]} {:s [3 4]}])

  (with-redefs [p/make-track
                (fn [arg]
                  (let [ret (rets @callno)]
                    (do
                      (swap! args conj arg)
                      (swap! callno inc)
                      ret)))]
    (is (= {:k [1 2] :s [3 4]}
           (p/make-pattern [1 2])))

    (is (= [1 2] @args))))

(defn dupa [])

(fact "make-pattern extracts tracks and merges them"
      (p/make-pattern .flat.) => .merged.
      (provided
        (p/make-tracks .flat.) => [.trk1. .trk2.]
        (p/merge-tracks .trk1. .trk2.) => .merged.))

(fact "make-tracks normalizes tracks and returns list of tracks"
      (p/make-tracks .flat.) => [.map1. .map2.]
      (provided
        (p/normalize-tracks .flat.) => [.trk1. .trk2.]
        (p/make-track .trk1.) => .map1.
        (p/make-track .trk2.) => .map2.))

(fact "merge-tracks concatenates all steps when mergind"
      (p/merge-tracks {:k [1 2] :s [9 8]} {:s [7 6] :h [5 5]})
        => {:k [1 2] :s [9 8 7 6] :h [5 5]})

(fact "normalize-tracks splits tracks and makes all velocities same length"
      (p/normalize-tracks .flat.) => [[.k1. .nv1. .p1.]
                                      [.k2. .nv2. .p2.]]
      (provided
        (p/split-on-keyword .flat.) => [[.k1. .v1. .p1.]
                                        [.k2. .v2. .p2.]]
        (p/cycle-to-same-length .v1. .v2.) => [.nv1. .nv2.]))

(fact
  "make-track builds a map of hit to steps"
  (p/make-track [.hit. .vels. .pits.]) => {.hit. .steps.}
  (provided
    (p/build-steps .vels. .pits.) => .steps.))

(fact
  "build map of velocities and pitches to its values"
  (p/build-steps [.v1. .v2.] [.p1.])
    => [{:vel .v1. :pit .p1.} {:vel .v2. :pit nil}]
  (provided
    (p/force-same-length [.v1. .v2.] [.p1.])
      => [[.v1. .v2.] [.p1. nil]]))

(fact
  "make-pattern works on real data"
  (p/make-pattern [:k [1 2 3 4] [1 2] :s [4 5 6] [9 8]])
    => {:k [{:vel 1 :pit 1}   {:vel 2 :pit 2}
            {:vel 3 :pit nil} {:vel 4 :pit nil}]
        :s [{:vel 4 :pit 9}   {:vel 5 :pit 8}
            {:vel 6 :pit nil} {:vel 4 :pit nil}]})

(require '[jumski.breakage.player :as p] :reload)
(check-facts)
;; (run-tests 'jumski.breakage.player-test)
