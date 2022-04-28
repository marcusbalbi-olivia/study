(ns hospital3.aula4
  (:require [schema.core :as s]))


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
  (let [paciente {:id id :nome nome :plano plano }]
    (if (not-empty nascimento) (into paciente {:nascimento nascimento}) paciente)))
        ([id :- PosInt
          nome :- s/Str
          plano :- Plano]
         (novo-paciente id nome plano "")))

(conj [1 2 3] 4)
(into [1 2 3] [4])

(novo-paciente 1 "Balbi" nil)
(novo-paciente 1 "Balbi" nil "25/07/2008")
(novo-paciente 1 "Balbi" [:raio-x] "25/07/2008")


(def Pacientes 
  { PosInt Paciente})

(println (s/validate Pacientes {}))
(println (s/validate Pacientes {1 (novo-paciente 1 "Isabela" nil)}))
;(println (s/validate Pacientes {10 {:not-paciente "Jonas"}}))
;(println (s/validate Pacientes {10 "Joao"}))
;(println (s/validate Pacientes {-10 (novo-paciente 1 "Isabela" nil)}))
(println (s/validate Pacientes {1 (novo-paciente 1 "Isabela" nil)
                                2 (novo-paciente 2 "Julia" nil)}))
;; (println (s/validate Pacientes {1 (novo-paciente 1 "Isabela" nil)
;;                                 1 (novo-paciente 2 "Julia" nil)}))




