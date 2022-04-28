(ns hospital3.aula3
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? 'inteiro-positivo))

(def Paciente
  {:id PosInt
   :nome s/Str})

(s/defn novo-paciente :- Paciente
        [id :- PosInt
         nome :- s/Str]
        {:id id :nome nome})


(novo-paciente 1 "Marcus Balbi")
;(novo-paciente -2 "Joao Silva")

(defn maior-ou-igual-zero?
  [x] (>= x 0))

(def ValorFinanceiro (s/constrained s/Num maior-ou-igual-zero?))

(def Pedido
  {:paciente Paciente
   :valor ValorFinanceiro
   :procedimento s/Keyword})


(s/defn novo-pedido :- Pedido
  [paciente :- Paciente
   valor :- ValorFinanceiro
   procedimento :- s/Keyword]
        { :paciente paciente :valor valor :procedimento procedimento })


(def balbi (novo-paciente 1 "Balbi"))

(novo-pedido balbi 25.90 :raio-x)
(novo-pedido balbi 0 :coleta-de-sangue)
;(novo-pedido "Balbi" 10 :coleta-de-sangue)
;(novo-pedido {:id 2 :nome "Joao" :idade 10} 10 :coleta-de-sangue)
;(novo-pedido balbi 25.90 "Raio-X")
;(novo-pedido balbi -5 :coleta-de-sangue)



