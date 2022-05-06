(ns hospital2.aula4)

(defrecord PacienteParticular [id nome nascimento situacao])
(defrecord PacientePlanoSaude [id nome nascimento situacao plano])


(defn nao-eh-urgente? [paciente]
  (not= :urgente (:situacao paciente :normal)))


(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(extend-type PacienteParticular Cobravel
             (deve-assinar-pre-autorizacao? [paciente procedimento valor]
               (and (> valor 50) (nao-eh-urgente? paciente))))

(extend-type PacientePlanoSaude Cobravel
             (deve-assinar-pre-autorizacao? [paciente procedimento valor]
               (let [plano (:plano paciente)]
                 (and (not (some #(= % procedimento) plano))
                      (nao-eh-urgente? paciente)))))


(println (nao-eh-urgente? (->PacienteParticular 10 "Joao" "25/01/2001" :normal)))

(let [particular (->PacienteParticular 10 "Balbi" "27/07/1989" :urgente)
       plano (->PacientePlanoSaude 15 "Joao" "25/03/1987" :urgente [:raio-x :ultrasom])]
   (println (deve-assinar-pre-autorizacao? particular :raio-x 500))
   (println (deve-assinar-pre-autorizacao? plano :raio-x 500))
   (println (deve-assinar-pre-autorizacao? plano :coleta-de-sangue 500)))

; nao se coloca multi no final 
(defmulti deve-assinar-pre-autorizacao-multi? class)
(defmethod deve-assinar-pre-autorizacao-multi? PacienteParticular [paciente] true)
(defmethod deve-assinar-pre-autorizacao-multi? PacientePlanoSaude [paciente] false)


(let [particular (->PacienteParticular 10 "Balbi" "27/07/1989" :urgente)
      plano (->PacientePlanoSaude 15 "Joao" "25/03/1987" :urgente [:raio-x :ultrasom])]
  (println (deve-assinar-pre-autorizacao-multi? particular))
  (println (deve-assinar-pre-autorizacao-multi? plano))
  (println (deve-assinar-pre-autorizacao-multi? plano)))



; explorando a fn que define a estrategia de um defmulti
(defn minha-funcao [p]
  (println p)
  p)
(defmulti multi-teste minha-funcao)

(multi-teste "Marcus Balbi")

; pedido {:paciente :valor :procedimento}

; mistura keyword com class no retorno, um pouco feiro
; isso e uma fn normal, podemos testar!
(defn tipo-de-autorizador [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)
        urgencia? (= :urgente situacao)]
    (if urgencia?
      :sempre-autorizado
      (class paciente))))

(defmulti deve-assinar-pre-autorizacao-pedido tipo-de-autorizador)

(defmethod deve-assinar-pre-autorizacao-pedido :sempre-autorizado [pedido] false)
(defmethod deve-assinar-pre-autorizacao-pedido PacienteParticular  [pedido]
  (>= (:valor pedido 0) 50))

(defmethod deve-assinar-pre-autorizacao-pedido PacientePlanoSaude  [pedido]
  (not (some #(= % (:procedimento pedido)) (get-in pedido [:paciente :plano]))))


(let [particular (->PacienteParticular 10 "Balbi" "27/07/1989" :urgente)
      plano (->PacientePlanoSaude 15 "Joao" "25/03/1987" :urgente [:raio-x :ultrasom])]
  (println (deve-assinar-pre-autorizacao-pedido {:paciente particular :valor 1000 :procedimento :raio-x }))
  (println (deve-assinar-pre-autorizacao-pedido {:paciente plano :valor 1000 :procedimento :coleta-de-sangue})))

;; (def pedido {
;;             :paciente (->PacienteParticular 10 "Marcus" "24/07/1989" :normal)
;;             :valor 65
;;             :procedimento :raio-x
;; })

;; (get-in pedido [:paciente :nascimento] 0)