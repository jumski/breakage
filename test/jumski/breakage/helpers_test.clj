(ns jumski.breakage.helpers-test)
(use 'clojure.test)
(require '[jumski.breakage.helpers :as h] :reload)

(deftest nil-placeholder
  (is (nil? h/_)))

(deftest make-beat
  (deftest appends-empty-pitches
    (let [beat (h/make-beat '(:k (1 k/_ k/_ k/_)))]
      (is (= '(1 k/_ k/_ k/_) (-> beat :k :volumes)))
      (is (= '(nil nil nil nil) (-> beat :k :pitches))))))

(require '[jumski.breakage.helpers :as h] :reload)
(run-tests 'jumski.breakage.helpers-test)
