(ns jumski.breakage.helpers-test)
(use 'clojure.test)
(require '[jumski.breakage.helpers :as h] :reload)

(deftest make-beat
  (deftest outputs-velocities
    (let [beat (h/make-beat [:k [1 nil]])]
      (is (= 1 (->> beat :k first :vel)))
      (is (nil? (->> beat :k last :vel)))))

  (deftest appends-nil-pitches
    (let [beat (h/make-beat [:k [1 nil]])]
      (is (nil? (->> beat :k first :pit)))
      (is (nil? (->> beat :k last :pit)))))

  (deftest missing-pitches
    (let [beat (h/make-beat [:k [1 2] [1]])]
      (is (nil? (->> beat :k last :pit)))))

  (deftest missing-velocities
    (let [beat (h/make-beat [:k [1 2]
                             :s [1]])]
      (is (nil? (->> beat :s last :vel)))))

  (deftest handles-pure-lists
    (let [beat (h/make-beat (list :k (list 1 2)))]
      (is (= 1 (->> beat :k first :vel)))
      (is (= 2 (->> beat :k last :vel))))))

(require '[jumski.breakage.helpers :as h] :reload)
(run-tests 'jumski.breakage.helpers-test)
