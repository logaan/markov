(ns markov.core-test
  (:require [clojure.test :refer :all]
            [markov.core :refer :all]))

(def text0
  "Hello world")

(def parsed0
  {:start  ["Hello"]
   "Hello" ["world"]
   "world" [:end]})

(deftest a-test
  (testing "Simplest case"
    (is (= parsed0 (from-string text0)))
    (is (= parsed0 (from-string-golf text0)))))

(def text1
  "The quick brown fox. Jumped over the lazy dog.")

(def parsed1
  {:start {"The" 1}})

(def text2
  "The slow brown fox. Jumped over many lazy dogs.")
