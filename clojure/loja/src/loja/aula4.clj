(ns loja.aula4
  (:require
    [loja.db :as l.db]
    [loja.logic :as l.logic]))

;(let [ resumo (l.logic/resumo-por-usuario (l.db/todos-os-pedidos))
;       pedidos (l.db/todos-os-pedidos)
;      ]
;  (println "Resumo" resumo)
;  (println "Ordenado" (sort-by :preco-total resumo))
;  (println "Ordenado mais caro" (reverse (sort-by :preco-total resumo)))
;  (println "Ordenado pelo ID inverso" (reverse (sort-by :id resumo)))
;  (println (get-in pedidos [0 :itens :mochila :quantidade])))


(defn resumo-por-usuario-ordenado [pedidos]
  (->> pedidos
       (l.logic/resumo-por-usuario)
       (sort-by :preco-total)
       (reverse)))

(defn top-2 [pedidos]
  (take 2 pedidos))


(let [
      pedidos (l.db/todos-os-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
        (println "Resumo" resumo)
        (println "Primeiro" (first resumo))
        (println "Segundo" (second resumo))
        (println "Resto" (rest resumo))
        (println "Total" (count resumo))
        (println "Classe" (class resumo))
        (println "Nth" (nth  resumo 1))
        (println "Take" (take 2 resumo))
        (println "Top 2" (top-2 resumo))
        (println "Maior que 500" (filter #(> (:preco-total %) 500) resumo))
        (println "Alguem gastou mais que 500" (some #(> (:preco-total %) 500) resumo))
        (println "Maior que 500 com not empty filter" (not (empty? (filter #(> (:preco-total %) 500) resumo)))))