(ns markov.core
  (:require [clojure.string :as str]))

(defn from-readable [string]
  (-> (str/lower-case string)
      (str/replace #"\." " .")
      (str/split #" ")))

(defn add-token [table [first second]]
  (update-in table [first] conj second))

(defn from-string
  ([string]
   (from-string {} string))

  ([table string]
   (let [parts  (from-readable string)
         tokens (concat [:start] parts [:end])
         pairs  (partition 2 1 tokens)]
     (reduce add-token table pairs))))

(defn to-readable [words]
  (-> (str/join " " words)
      (str/replace #" \." ".")))

(defn generate [table]
  (->> :start
       (iterate #(rand-nth (table %)))
       (remove keyword?)
       (take-while some?)
       to-readable))
