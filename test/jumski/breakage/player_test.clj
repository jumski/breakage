(ns jumski.breakage.player-test)
(use 'clojure.test)
(use 'conjure.core)
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

(run-tests 'jumski.breakage.player-test)
