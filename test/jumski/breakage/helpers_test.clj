(ns jumski.breakage.helpers
  (:use [clojure.test]
        [clojure.contrib.mock])
  (:require [jumski.breakage.helpers :as h :reload]))

(def _ h/_)

(def input
  [:k [1 _ 1 _]
   :s [1 _ _ _ 1 _ _ _]
      [_ _ _ _ 2 _ _ _]
   :h [1 _]
      [_ _ 1 _]
   ])

(deftest returns-proper-output
  (is (= (h/make-beat input)
         {:k {:hits    [1 _ 1 _ 1 _ 1 _]
              :pitches [_ _ _ _ _ _ _ _]}
          :s {:hits    [1 _ _ _ 1 _ _ _]
              :pitches [_ _ _ _ 2 _ _ _]}
          :h {:hits    [1 _ 1 _ 1 _ 1 _]
              :pitches [_ _ 1 _ _ _ 1 _]}}
         )))
