(ns jumski.breakage.player-test)
(use 'clojure.test)
(require '[jumski.breakage.player :as p] :reload)

(defn mkdummy [len]
  (take len (iterate inc 0)))

(deftest beat-to-play
  (is (= (list 0 1 2 3) (p/beat-to-play 1 (mkdummy 8))))
  (is (= (list 0 1 2 3) (p/beat-to-play 9 (mkdummy 8))))
  (is (= (list 4 5 6 7) (p/beat-to-play 2 (mkdummy 8))))
  (is (= (list 4 5 6 7) (p/beat-to-play 10 (mkdummy 8)))))

;; (require '[jumski.breakage.player :as p] :reload)
(run-tests 'jumski.breakage.player-test)
