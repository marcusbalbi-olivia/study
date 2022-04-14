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


;(defrecord Paciente [^Long id ^String nome nascimento])
(defrecord Paciente [id nome nascimento])

(clojure.pprint/pprint (->Paciente 3 "Julia Balbi" "25/11/2019"))

(Paciente. 4 "Fabiano" "10/10/2010")
(Paciente. "Fabiano" 52 "10/10/2010")

(map->Paciente {:id 15 :nome "Daniel" :nascimento "24/04/1985"})

(let [balbi (->Paciente 1 "Balbi" "27/07/1989")]
  (println (:id balbi))
  (println (vals balbi))
  (println (into balbi { :rg "12345679" }))
  (println (assoc balbi :id 25))
  (class balbi)
  (record? balbi)
  (println (.nome balbi)))

;(Paciente. "Fabiano" "10/10/2010")

(= (->Paciente 1 "Balbi" "27/07/1989") (->Paciente 1 "Balbi" "27/07/1989"))
(= (->Paciente 1 "Balbi" "27/07/1989") (->Paciente 1 "Balbi" "27/07/189"))

