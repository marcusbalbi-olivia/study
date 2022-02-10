(ns loja.aula3
  (:require [loja.db :as l.db]))

(println (l.db/todos-os-pedidos))

(println (group-by :usuario (l.db/todos-os-pedidos)))

(defn minha-funcao-agrupamento
  [elemento]
  (println elemento)
  (:usuario elemento))

(println (group-by minha-funcao-agrupamento (l.db/todos-os-pedidos)))


(println (map count (vals (group-by :usuario   (l.db/todos-os-pedidos)))))



(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     vals
     (map count)
     println)


(defn conta-total-por-usuario
  [[usuario, pedidos]]
  { :usuario usuario :qtde-itens (count pedidos)}
  )

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-total-por-usuario)
     println)

(defn total-do-item [[chave detalhes]]
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0 )))

(defn total-do-pedido [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)
       ))

(defn total-dos-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn quantia-de-pedidos-e-gasto-total-por-usuario
  [[usuario, pedidos]]
  { :usuario usuario
    :total-pedido (count pedidos)
    :preco-total (total-dos-pedidos pedidos)
   }
  )

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map quantia-de-pedidos-e-gasto-total-por-usuario) println)

;(println (total-item {:quantidade 2 :preco-unitario 80}))