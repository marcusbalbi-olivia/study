(ns hospital3.aula1 
  (:require [clojure.pprint :as pprint]))

(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui ID" {:paciente paciente}))))

; { 15 [], 20 [], 25 {} }
(defn adiciona-visita [visitas paciente novas-visitas]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))


(defn imprime-relatorio-de-paciente [visitas paciente]
  (println "Visitas do paciente " paciente "são:" (get visitas paciente)))

(defn testa-usa-de-pacientes []
  (let [guilherme {:id 1 :nome "Guilherme"}
        daniela {:id 2 :nome "Daniela"}
        paulo {:id 3 :nome "Paulo"}
        pacientes (reduce adiciona-paciente {} [guilherme daniela paulo])
        visitas {}
        visitas (adiciona-visita visitas 15 ["01/01/2019"])
        visitas (adiciona-visita visitas 20 ["01/02/2019" "01/01/2020"])
        visitas (adiciona-visita visitas 15 ["01/03/2019"])
        ]
    (pprint/pprint visitas)
    (imprime-relatorio-de-paciente visitas guilherme)
    ))


(testa-usa-de-pacientes)

