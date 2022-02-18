(ns hospital.entendendopartials)




(defn soma [n1 n2] (+ n1 n2))

(println (soma 2 2 ))

(let [
      soma2 (partial soma 2)
      soma10 (partial soma 10)
      ]
  (println (soma2 5))
  (println (soma2 15))
  (println (soma10 90))
  )