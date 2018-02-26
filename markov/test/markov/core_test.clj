(ns markov.core-test
  (:require [clojure.test :refer :all]
            [markov.core :refer :all]))

(def text0
  "Hello world")

(def parsed0
  {:start  ["hello"]
   "hello" ["world"]
   "world" [:end]})

(deftest a-test
  (testing "Simplest case"
    (is (= parsed0 (from-string text0)))))

(def texts
  ["The quick brown fox. Jumped over the lazy dog."
   "The slow brown fox. Jumped over many lazy dogs."])

(def parsed1
  {"dog"    ["."]
   "fox"    ["." "."]
   "over"   ["many" "the"]
   "slow"   ["brown"]
   :start   ["the" "the"]
   "many"   ["lazy"]
   "brown"  ["fox" "fox"]
   "jumped" ["over" "over"]
   "quick"  ["brown"]
   "."      [:end "jumped" :end "jumped"]
   "lazy"   ["dogs" "dog"]
   "dogs"   ["."]
   "the"    ["slow" "lazy" "quick"]})

(deftest another-test
  (testing "Two strings case"
    (is (= parsed1
           (-> (from-string (nth texts 0))
               (from-string (nth texts 1)))))))

(comment

  (generate parsed1)

  )
