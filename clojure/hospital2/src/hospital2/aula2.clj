(ns hospital2.aula2
  (:require [hospital2.model :refer [to-ms]]))


;(defrecord Paciente [id nome nascimento])
; Paciente Plano de Saude  =>  + plano de saude
; Paciente Particular  =>  + 0


(defrecord PacienteParticular [id nome nascimento])
(defrecord PacientePLanoSaude [id nome nascimento plano])


; regras diferentes para tipos diferentes
;; deve-assinar-pre-autorizacao
;;  Particular => Valor maior que 50 reais
;; PLanoSaude => Procedimento não esta no plano


(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(extend-type PacienteParticular Cobravel
             (deve-assinar-pre-autorizacao? [paciente procedimento valor]
               (> valor 50)))

(extend-type PacientePLanoSaude Cobravel
             (deve-assinar-pre-autorizacao? [paciente procedimento valor]
               (let [plano (:plano paciente)]
                 (not (some #(= % procedimento) plano)))
               ))


;; implementar diretamente
;; (defrecord PacienteParticular [id nome nascimento] 
;;   Cobravel
;;   (deve-assinar-pre-autorizacao? [paciente procedimento valor]
;;                                  (> valor 50)))


(let [particular (->PacienteParticular 10 "Balbi" "27/07/1989")
      plano (->PacientePLanoSaude 15 "Joao" "25/03/1987"[:raio-x :ultrasom])]
  (println (deve-assinar-pre-autorizacao? particular :raio-x 500))
  (println (deve-assinar-pre-autorizacao? plano :raio-x 500))
  (println (deve-assinar-pre-autorizacao? plano :coleta-de-sangue 500)))


(to-ms 10)
(to-ms (java.util.Date.))
(to-ms (java.util.GregorianCalendar.))