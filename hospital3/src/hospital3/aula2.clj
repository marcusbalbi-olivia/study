(ns hospital3.aula2
    (:require [clojure.pprint :as pprint]
              [schema.core :as s]))

(s/set-fn-validation! true)
;(defrecord Paciente [id nome])
;; (s/defrecord Paciente 
;;              [id :- Long nome :- s/Str])
;; (map->Paciente { :id "a0b1" :nome 12})
;; (Paciente. "a0b1" 34)

;; (def Endereco "Schema de um Endereco"
;;   {:logradoutro s/Str :cep s/Str})

;; (def Paciente
;;   "Schema de um Paciente"
;;    {:id s/Num :nome s/Str :endereco Endereco})
;; (s/explain Paciente)
;; (s/validate Paciente { :id 20 :nome "Joao" :endereco { :logradoutro "Teste" :cep "28625530"}})
;; (s/validate Paciente { :id 20 :name "Joao" :endereco { :logradoutro "Teste" :cep "28625530"}})
;; (s/validate Paciente { :id 20 :nome 33 })
;; (s/validate Paciente {:id 20 :nome "Joao" :endereco {:logradoutro "Teste" :cep "28625530"} :plano [:raio-x :coleta-de-sangue]})

(def Paciente
  "Schema de um Paciente"
  {:id s/Num :nome s/Str})

;; (s/defn novo-paciente :- Paciente
;;         [id :- s/Num nome :- s/Str]
;;         {:id id :nome nome :plano [] })

(s/defn novo-paciente :- Paciente
  [id :- s/Num nome :- s/Str]
  {:id id :nome nome })

(novo-paciente 15 "Balbi")


(defn estritamente-positivo?  [x]
  (> x 0))

(def EstritamentePositivo (s/pred estritamente-positivo? 'estritamente-positivo))

(s/validate EstritamentePositivo 20)
(s/validate EstritamentePositivo -20)
(s/validate EstritamentePositivo 0)
(s/validate EstritamentePositivo "Not a number")
