(ns estoque.aula6)

(def pedido {:mochila { :quantidade 2, :preco 80 }
             :camiseta { :quantidade 3, :preco 25 }})

(defn imprime-e-15
  [valor]
  (println "VALOR" valor (class valor))
  15)

(println (map imprime-e-15 pedido))

(defn imprime-e-vals
  [[chave valor]]
  (println "chave" chave "e valor" valor)
  valor)

(println (map imprime-e-vals pedido))

(defn preco-dos-produtos
  [[_, valor]]
  (* (:quantidade valor) (:preco valor)))

(defn total-do-pedido [pedido]
  (reduce + (map preco-dos-produtos pedido)))


(println (map preco-dos-produtos pedido))
(println (total-do-pedido pedido))



(defn total-do-pedido2 [pedido]
  (->> pedido                                               ;; last
      (map preco-dos-produtos)
      (reduce +)))

(println (total-do-pedido2 pedido))


(defn preco-total-produto
  [produto]
  (println "CHEGUEI AQUI COM O PRPDUTO" produto)
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido3 [pedido]
  (->> pedido                                           ;; last
       vals
       (map preco-total-produto)
       (reduce +)))

(println (total-do-pedido3 pedido))
;(println (map preco-total-produto (vals pedido)))
