(ns markov.core
  (:require [clojure.string :as str]))

(defn inc-token [table [first second]]
  (update-in table [first] conj second))

(defn from-string
  ([string]
   (from-string {} string))
  ([table string]
   (let [parts (-> string
                   (str/lower-case)
                   (str/replace #"\." " .")
                   (str/split #" "))
         tokens (concat [:start] parts [:end])
         pairs  (partition 2 1 tokens)]
     (reduce inc-token table pairs))))

(defn generate
  ([table]
   (let [joined (->> (generate table :start)
                  (take-while #(not (= :end %)))
                  rest
                  (str/join " "))]
     (str/replace joined #" \." ".")))
  ([table word]
   (let [next (rand-nth (table word))]
     (cons word (lazy-seq (generate table next))))))
