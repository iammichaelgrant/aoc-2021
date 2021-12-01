(ns day_1)

(def input (slurp "resources/input_day_1.txt"))
(def example "199\n200\n208\n210\n200\n207\n240\n269\n260\n263")

(def measurements (map #(Integer/parseInt %) (clojure.string/split-lines input)))

;;Part 1
(loop [previous nil
       current (first measurements)
       following (rest measurements)
       changes []]
  (if (nil? current)
    (->> changes
         (filter #(= :inc %))
         count)
    (recur
      current
      (first following)
      (rest following)
      (conj changes (cond
                      (nil? previous) :none
                      (< previous current) :inc
                      (> previous current) :dec
                      :else :unknown)))))

;;Part 2
(loop [previous nil
       current (apply + (take 3 measurements))
       following (rest measurements)
       changes []]
  (if (< (count following) 2)
    (->> changes
         (filter #(= :inc %))
         count)
    (recur
      current
      (apply + (take 3 following))
      (rest following)
      (conj changes (cond
                      (nil? previous) :none
                      (< previous current) :inc
                      (> previous current) :dec
                      :else :unknown)))))
