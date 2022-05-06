(ns hospital.aula6
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn cabe-na-fila? [fila]
  (-> fila
      count
      (< 5)))


(defn chega-em [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

(defn chega-em-refset!
  "troca de referencia via ref-set"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em @fila pessoa))
    ))

(defn chega-em!
  "Troca de ref via alter"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia []
  (let [hospital {:espera (ref h.model/fila_vazia)
                  :laboratorio1 (ref h.model/fila_vazia)
                  :laboratorio2 (ref h.model/fila_vazia)
                  :laboratorio3 (ref h.model/fila_vazia)
                  }]
    (dosync
      (chega-em! hospital "Pedro")
      (chega-em! hospital "Joao")
      (chega-em! hospital "Carlos")
      (chega-em! hospital "Mario")
      ; (chega-em! hospital "Fabiano")
      (chega-em! hospital "Augusto"))
    (pprint hospital)
    ))

;(simula-um-dia)



(defn async-chega-em! [hospital pessoa]
  (future (Thread/sleep (rand 5000))
          (dosync
            (println "Tetnando Inserir pessoa " pessoa)
            (chega-em! hospital pessoa))))

(defn simula-um-dia-async []
  (let [hospital {:espera (ref h.model/fila_vazia)
                  :laboratorio1 (ref h.model/fila_vazia)
                  :laboratorio2 (ref h.model/fila_vazia)
                  :laboratorio3 (ref h.model/fila_vazia)
                  }
        futures (mapv (fn [pessoa] (async-chega-em! hospital pessoa)) (range 10))]
      (future
        (dotimes [n 4]
           (Thread/sleep 2000)
           (pprint hospital)
           (pprint futures)))))


(simula-um-dia-async)

;(println (future 15))
;(println (future ((Thread/sleep 5000) 15)))