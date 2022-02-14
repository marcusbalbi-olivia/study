(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))


(defn simula-um-dia []
  (def hospital (h.model/novo-hospital))
  (def hospital (h.logic/chega-em hospital :espera "111"))
  (def hospital (h.logic/chega-em hospital :espera "222"))
  (def hospital (h.logic/chega-em hospital :espera "333"))
  (def hospital (h.logic/chega-em hospital :espera "444"))
  (def hospital (h.logic/chega-em hospital :espera "888"))
  (def hospital (h.logic/chega-em hospital :espera "999"))
  (pprint hospital)
  (def hospital (h.logic/chega-em hospital :laboratorio1 "555"))
  (def hospital (h.logic/chega-em hospital :laboratorio3 "777"))
  (pprint hospital)
  (def hospital (h.logic/atende hospital :laboratorio1))
  (def hospital (h.logic/atende hospital :laboratorio3))
  (pprint hospital)
  )


;(simula-um-dia)

(defn chega-em-malvado [pessoa]
  (def hospital (h.logic/chega-em-pausado hospital :espera pessoa))
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo []
  ; Simula o problema de variavel compartilhada em um ambiente multithread
  (def hospital (h.model/novo-hospital))
  (.start (Thread. (fn [] (chega-em-malvado "111"))))
  (.start (Thread. (fn [] (chega-em-malvado "222"))))
  (.start (Thread. (fn [] (chega-em-malvado "333"))))
  (.start (Thread. (fn [] (chega-em-malvado "444"))))
  (.start (Thread. (fn [] (chega-em-malvado "555"))))
  (.start (Thread. (fn [] (chega-em-malvado "666"))))
  (.start (Thread. (fn []
                           (Thread/sleep 4000)
                           (pprint hospital)
                            ))))

(simula-um-dia-em-paralelo)