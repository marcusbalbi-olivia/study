(ns hospital2.aula1)


;pacientes { 15 {paciente} 23 {paciente} }
(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui um id" {:paciente paciente}))))


(defn teste-pacientes []
  (let [
        pacientes {}
        marcus {:id 1 :nome "Marcus Balbi" :nascimento "27/07/1989"}
        isabela {:id 2 :nome "Isabela Balbi" :nascimento "15/01/1990"}
        julia { :nome "Julia Balbi" :nascimento "25/11/2019"}
        ]
    ;(reduce adiciona-paciente [marcus isabela julia], pacientes)
    (adiciona-paciente pacientes julia)
    ))

(clojure.pprint/pprint (teste-pacientes))
