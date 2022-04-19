(ns hospital3.aula1 
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))



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


(s/validate Long 15) ;; devolve o proprio valor quando valido
(s/validate Long "Marcus")
(s/validate Long [1 2 3 4 5])


;; declarativo nao valida
(s/defn teste-simples [x :- Long]
        (println x))
(teste-simples 12)
(teste-simples "Teste")


;; define explicatamente que deve validar na forma declarativa
(s/set-fn-validation! true)
(s/defn teste-simples [x :- Long]
  (println x))
(teste-simples 12)
(teste-simples "Teste")

(s/defn imprime-relatorio-de-paciente [visitas paciente :- Long]
  (println "Visitas do paciente " paciente "são:" (get visitas paciente)))

(testa-usa-de-pacientes)

(s/defn novo-paciente [id :- Long nome :- s/Str]
        {:id id :nome nome})

(novo-paciente 15 "Balbi") 