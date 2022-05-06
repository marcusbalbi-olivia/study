(ns estoque.aula5)

(def estoque {"Mochila" 10, "Camiseta" 5})

(println estoque)

(println "Temos " (count estoque) "Itens")
(println "Chaves são" (keys estoque))
(println "Valores são" (vals estoque))

;; keyword
;; :mochila
(def estoque {:mochila  10
              :camiseta 5})

(println (assoc estoque :cadeira 3))
(println (assoc estoque :mochila 500))

(println (update estoque :mochila inc))

(defn tira-um
  [x]
  (println "Retirando 1")
  (- x 1))

(println (update estoque :mochila tira-um))
(println (update estoque :mochila #(- % 3)))


(println (dissoc estoque :mochila))


(def pedido {:mochila { :quantidade 2, :preco 80 }
             :camiseta { :quantidade 3, :preco 25 }})

(println "\n\n\n\n\n")
(println pedido)

(def pedido (assoc pedido :chaveiro { :quantidade 1, :preco 0.1 }))
(println (pedido :mochila))
(println (get pedido :mochila))
(println (get pedido :cadeira {}))

(println (:mochila pedido {}))
(println (:mochilat pedido {}))

(println (:preco (:mochila pedido)))
(println "Pegando com get-in o preco da mochila" (get-in pedido [:mochila :preco]))

(println (update-in pedido [:mochila :quantidade] inc))

(println "Pegando a quantidade de mochilas com threading" (-> pedido
    :mochila
    :quantidade
    ))

(-> pedido :chaveiro :quantidade println)