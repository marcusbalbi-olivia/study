(ns loja.aula5
  (:require
    [loja.db :as l.db]
    [loja.logic :as l.logic]))

(defn gastou-bastante? [info-usuario] (> (:preco-total info-usuario) 500))

(let [
      pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
      (println "keep" (keep gastou-bastante?  resumo))
      (println "Filter" (filter gastou-bastante?  resumo)))


(defn gastou-bastante? [info-usuario]
  (println "entendendo leep e filter" (:usuario info-usuario))
  (> (:preco-total info-usuario) 500))

(let [
      pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "keep" (keep gastou-bastante?  resumo))
  (println "Filter" (filter gastou-bastante?  resumo)))


(println "Vamo isolar....")

(println (range 10))
(println (take 2 (range 10)))
(println (take 2 (range 1000000000000000)))
; A sequencia nao esta sendo gulosa ou  "Eager"

(let [sequencia  (range 100000)]
  (println (take 2 sequencia))
  (println (take 2 sequencia))                              ; Imutabilidade, sequencia e a msm
  )                                                         ; Esta sendo Lazy


(println "\n\n\nMAPS lazy and eagern\n\n")

(defn filtro1 [x1] (println "Filtro1" x1) x1)

(defn filtro2 [x1] (println "Filtro2" x1) x1)

(println (map filtro2 (map filtro1 (range 10))))

; CHUNKS de 32
; Hibrido entre Lazy e Eager
(->> (range 50)
     (map filtro1)
     (map filtro2)
     println)


; Forca executar eager
(->> (range 50)
     (mapv filtro1)
     (mapv filtro2)
     println)


(->> [0 1 2 3 4 5 2 1 2 3 2 1 2 5 78 89  5 6 5 4 0 2 1 4 5 6 8 7 14 5 0 66 4 56 54 9 8 2 0 5 8 5 6 8 6 14 2 2 2 1 2 3 4 8]
     (map filtro1)
     (map filtro2)
     println)

; lista ligada 100% lazy
(->> '(0 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 0 1 2 3 5 2 0 0 5 4 6 85 0 1 2 3 4 5 6 7 8 9 0 3 2 1 4 5)
     (map filtro1)
     (map filtro2)
     println)