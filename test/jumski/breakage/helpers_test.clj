(ns jumski.breakage.helpers-test)
(use 'clojure.test)
(require '[jumski.breakage.helpers :as h] :reload)

(def full-pattern
  [:kick [1 _ _ _]
         [1 _ _ _]
   :hat  [1 _ 1 _]])

(deftest nil-placeholder
  (is (nil? h/_)))

(deftest make-beat
  (deftest appends-empty-pitches
    (let [b (h/make-beat '(:k (1 k/_ k/_ k/_)))]
      (is (= '(1 k/_ k/_ k/_) (-> b :k :volumes)))
      (is (= '(nil nil nil nil) (-> b :k :pitches))))))

(run-tests 'jumski.breakage.helpers-test)
