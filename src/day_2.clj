(ns day_2)

(def input (slurp "resources/input_day_2.txt"))
(def example "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2")

(def commands
  (->> (clojure.string/split-lines input)
       (map #(clojure.string/split % #" "))))

;;Part 1
(loop [position {:forward 0
                 :depth 0}
       commands commands]
  ;(println position commands)
  (if (empty? commands)
    (* (:forward position) (:depth position))
    (let [command (first commands)
          direction (first command)
          value (Integer/parseInt (second command))
          forward (if (= direction "forward")
                    value
                    0)
          depth (case direction
                  "down" value
                  "up" (- value)
                  0)]
      (recur
        (-> position
            (update :forward #(+ forward %))
            (update :depth #(+ depth %)))
        (rest commands)))))

;;Part 2
(loop [position {:forward 0
                 :depth 0
                 :aim 0}
       commands commands]
  ;(println position commands)
  (if (empty? commands)
    (* (:forward position) (:depth position))
    (let [command (first commands)
          direction (first command)
          value (Integer/parseInt (second command))
          aim (case direction
                "down" value
                "up" (- value)
                0)
          horizontal (if (= direction "forward")
                    value
                    0)
          depth (if (= direction "forward")
                  (* (:aim position ) value)
                  0)]
      (recur
        (-> position
            (update :forward #(+ % horizontal))
            (update :depth #(+ % depth))
            (update :aim #(+ % aim)))
        (rest commands)))))
