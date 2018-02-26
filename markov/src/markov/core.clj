(ns markov.core
  (:require [clojure.string :as str]))

(def new-table
  {})

(defn inc-token [table [first second]]
  (update-in table [first] conj second))

(defn from-string [string]
  (let [parts  (str/split string #" ")
        tokens (concat [:start] parts [:end])
        pairs  (partition 2 1 tokens)]
    (reduce inc-token new-table pairs)))

(defn from-string-golf [string]
  (->> (concat [:start] (str/split string #" ") [:end])
       (partition 2 1)
       (reduce (fn [t [f s]] (update-in t [f] conj s)) {})))
