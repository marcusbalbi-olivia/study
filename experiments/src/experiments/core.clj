(ns experiments.core
  (:gen-class))

(def colors ["red" "green"])
(println colors)
(println (conj colors "blue"))
(println colors)
(println (into colors ["blue"]))
(println (assoc colors (count colors) "blue"))

;; not pure
(defn hello-world []
  "provides an hour-appropriate greeting string"
  (let [now (java.time.LocalTime/now)]
    (if (< (.getHour now) 12)
      (println "Good Morning")
      (println "Good Afternoon"))))
(hello-world)

;; pure
(defn greetings [hour]
  "provides an hour-appropriate greeting string"
  (if (< hour 12)
    "Good Morning"
    "Good Afternoon"))

(greetings 10)
(greetings 13)


(defn greetings-improved [hour]
  "provides an hour-appropriate greeting string"

  (cond
    (< hour 12)  "Good Morning"
    (< hour 17)  "Good Afternoon"
    (< hour 20)  "Good Evening"
    (>= hour 21) "Good Night"))

(greetings 10)
(greetings 13)
(greetings 18)
(greetings 23)




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
