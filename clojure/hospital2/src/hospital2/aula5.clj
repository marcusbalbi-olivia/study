(ns hospital2.aula5)

(defn tipo-de-autorizador [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)]
    (cond (= :urgente situacao) :sempre-autorizado
          (contains? paciente :plano) :plano-de-saude
          :else :credito-minimo)))


(defmulti deve-assinar-pre-autorizacao? tipo-de-autorizador)

(defmethod deve-assinar-pre-autorizacao? :sempre-autorizado [pedido] false)
(defmethod deve-assinar-pre-autorizacao? :plano-de-saude [pedido]
  (not (some #(= % (:procedimento pedido)) (get-in pedido [:paciente :plano]))))

(defmethod deve-assinar-pre-autorizacao? :credito-minimo [pedido]
  (>= (:valor pedido 0) 50))


(let [particular {:id 10 :nome "Balbi" :nascimento "27/07/1989" :situacao :normal}
      plano {:id 15 :nome "Joao D" :nascimento "05/03/1974" :situacao :normal :plano [:raio-x :ultrasom]}]
  (println (deve-assinar-pre-autorizacao? {:paciente particular :valor 1000 :procedimento :raio-x}))
  (println (deve-assinar-pre-autorizacao? {:paciente plano :valor 1000 :procedimento :coleta-de-sangue})))

