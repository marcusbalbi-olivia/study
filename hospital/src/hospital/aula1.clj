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
  (pprint hospital)
  (def hospital (h.logic/chega-em hospital :laboratorio1 "555"))
  (def hospital (h.logic/chega-em hospital :laboratorio3 "777"))
  (pprint hospital)
  (def hospital (h.logic/atende hospital :laboratorio1))
  (def hospital (h.logic/atende hospital :laboratorio3))
  (pprint hospital)
  )


(simula-um-dia)