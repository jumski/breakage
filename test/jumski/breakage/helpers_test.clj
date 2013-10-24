(ns jumski.breakage.helpers-test)
(use 'clojure.test)
(require '[jumski.breakage.helpers :as h] :reload)

(deftest make-beat
  (deftest appends-empty-pitches-and-outputs-volumes
    (let [beat (h/make-beat [:k [1 nil]])]
      (is (= {:k '({:vel 1 :pit nil}
                   {:vel nil :pit nil})}
             beat)))))
      ;; (is (= '(1 nil nil nil) (-> beat :k :volumes)))
      ;; (is (= '(nil nil nil nil) (-> beat :k :pitches)))))

  ;; (deftest fills-in-blanks
  ;;   (let [beat (h/make-beat [:k [1 2] :s [1]])]
  ;;     (is (= '(1 nil) (-> beat :s :volumes))))))

(require '[jumski.breakage.helpers :as h] :reload)
(run-tests 'jumski.breakage.helpers-test)
