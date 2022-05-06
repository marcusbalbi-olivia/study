(ns loja.logic)


(defn conta-total-por-usuario
  [[usuario, pedidos]]
  { :usuario usuario :qtde-itens (count pedidos)}
  )

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
   })

(defn resumo-por-usuario
  [pedidos]
  (->> pedidos
       (group-by :usuario)
       (map quantia-de-pedidos-e-gasto-total-por-usuario)))