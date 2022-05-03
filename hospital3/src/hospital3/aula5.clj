(ns hospital3.aula5
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))


(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? 'inteiro-positivo))

(def Plano [s/Keyword])

(def Paciente
  {:id PosInt
   :nome s/Str
   :plano Plano
   (s/optional-key :nascimento) s/Str})

(s/defn novo-paciente :- Paciente
  ([id :- PosInt
    nome :- s/Str
    plano :- Plano
    nascimento :- s/Str]
   (let [paciente {:id id :nome nome :plano plano}]
     (if (not-empty nascimento) (into paciente {:nascimento nascimento}) paciente)))
  ([id :- PosInt
    nome :- s/Str
    plano :- Plano]
   (novo-paciente id nome plano "")))


(def Pacientes
  {PosInt Paciente})

(def Visitas {PosInt [s/Str]})


(s/defn adiciona-paciente :- Pacientes
  [pacientes :- Pacientes paciente :- Paciente]
  (let [id (:id paciente)]
    (assoc pacientes id paciente)))

; { 15 [], 20 [], 25 {} }
(s/defn adiciona-visita :- Visitas
  [visitas :- Visitas paciente :- PosInt novas-visitas :- [s/Str]]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))


(s/defn imprime-relatorio-de-paciente[visitas :- Visitas paciente :- PosInt]
  (println "Visitas do paciente " paciente "são:" (get visitas paciente)))

(defn testa-usa-de-pacientes []
  (let [guilherme {:id 1 :nome "Guilherme" :plano []}
        daniela {:id 2 :nome "Daniela" :plano []}
        paulo {:id 3 :nome "Paulo" :plano []}
        pacientes (reduce adiciona-paciente {} [guilherme daniela paulo])
        visitas {}
        visitas (adiciona-visita visitas 1 ["01/01/2019"])
        visitas (adiciona-visita visitas 1 ["01/02/2019" "01/01/2020"])
        visitas (adiciona-visita visitas 2 ["01/03/2019"])]
    (pprint/pprint visitas)
    (imprime-relatorio-de-paciente visitas (:id guilherme))))
(testa-usa-de-pacientes)


