(ns loja.aula2)

; ["Maria" "Pedro" "Augusto" "Fabiana"]

;(defn conta
;  [total-ate-agora elementos]
;  (recur (inc total-ate-agora) (rest elementos)))


(defn minha-funcao
  "TESTE assinatura"
  ([p1] (println p1))
  ([] (println "No parameters"))
  )

(minha-funcao)
(minha-funcao "TESTE 1 Parametro")

(defn conta

  ([elementos]
   (conta 0 elementos))

  ([total-ate-agora elementos]
  (if (seq elementos)
    (recur (inc total-ate-agora) (next elementos))
    total-ate-agora)))

(println (conta 0 ["Maria" "Pedro" "Augusto" "Fabiana"]))
(println (conta ["Maria" "Pedro" "Augusto" "Fabiana"]))


(defn conta-loop
  [elementos]
  (loop [total-ate-agora 0 elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(println (conta-loop ["Maria" "Pedro" "Augusto" "Fabiana"]))