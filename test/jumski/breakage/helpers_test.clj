(ns jumski.breakage.helpers-test)
(use 'clojure.test)
(require '[jumski.breakage.helpers :as h] :reload)

(deftest nil-placeholder
  (is (nil? h/_)))

(deftest make-beat
  (deftest appends-empty-pitches
    (let [b (h/make-beat '(:k (1 k/_ k/_ k/_)))]
      (and (is (= '(1 k/_ k/_ k/_) (-> b :k :volumes)))
           (is (= '(nil nil nil nil) (-> b :k :pitches)))))))

(require '[jumski.breakage.helpers :as h] :reload)
(run-tests 'jumski.breakage.helpers-test)
