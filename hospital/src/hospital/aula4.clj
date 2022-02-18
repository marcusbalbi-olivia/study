(ns hospital.aula4
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

; swap! faz retries para tentar inserir no atamo
(defn chega-em-malvado! [hospital departamento pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando departamento pessoa)
  (println "apos inserir" pessoa))

(defn chega-em-normal! [hospital departamento pessoa]
  (swap! hospital h.logic/chega-em departamento pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo-com-mapv
  [chega-em departamento]
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]
    (mapv (fn [pessoa] (.start (Thread. (fn [] (chega-em hospital departamento pessoa))))) pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint @hospital)
                       )))))

(defn simula-um-dia-em-paralelo-com-mapv-refatorada
  [chega-em departamento]
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta-thread-com-chegada (fn [pessoa] (.start (Thread. (fn [] (chega-em hospital departamento pessoa)))))]
    (mapv starta-thread-com-chegada pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint @hospital)
                       )))))

(defn starta-thread-de-chegada [chega-em hospital departamento pessoa]
  (.start (Thread. (fn [] (chega-em hospital departamento pessoa)))))

;
;(defn preparada [chega-em hospital departamento]
;  (fn [pessoa] (starta-thread-de-chegada chega-em hospital departamento pessoa)))

(defn simula-um-dia-em-paralelo-com-mapv-refatorada-fn-extraida
  [chega-em departamento]
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta (partial starta-thread-de-chegada chega-em hospital departamento)
        ]

    (mapv starta pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint @hospital)
                       )))))



(defn simula-um-dia-em-paralelo-com-doseq
  [chega-em departamento]
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]

    (doseq [pessoa pessoas]
      (starta-thread-de-chegada chega-em hospital departamento pessoa))

    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint @hospital)
                       )))))


(defn simula-um-dia-em-paralelo-com-dotimes
  [chega-em departamento]
  (let [hospital (atom (h.model/novo-hospital))]

    (dotimes [pessoa 10]
      (starta-thread-de-chegada chega-em hospital departamento pessoa))

    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint @hospital)
                       )))))



;(simula-um-dia-em-paralelo chega-em-malvado! :espera)
(simula-um-dia-em-paralelo-com-dotimes chega-em-malvado! :laboratorio1)