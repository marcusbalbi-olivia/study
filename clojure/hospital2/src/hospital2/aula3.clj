(ns hospital2.aula3 
  (:require [hospital2.logic :as h.logic]))

(defn carrega-paciente [id]
  (println "carregando o paciente" id)
  (Thread/sleep 1000)
  {:id id :nome "Balbi" :carregado-em (h.logic/agora)})


(carrega-paciente 15)

;; (defn carrega-se-nao-existe [pacientes id]
;;   (let [paciente-carregado (find pacientes #(= (:id %) id))]
;;     (if (nil? paciente-carregado)
;;       (carrega-paciente id)
;;       paciente-carregado)))

(defn carrega-se-nao-existe [cache id carregadora]
  (if (contains? cache id)
    cache
    (let [item (carregadora id)]
      (assoc cache id item))))


(let [pacientes {}]
  (-> pacientes
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 20 carrega-paciente)
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 10 carrega-paciente)
      (carrega-se-nao-existe 100 carrega-paciente)
      (carrega-se-nao-existe 20 carrega-paciente)))


(defprotocol Carregavel
  (carrega! [this id]))


(defrecord Cache [cache carregadora]
  Carregavel
  (carrega! [this id]
            (swap! cache carrega-se-nao-existe id carregadora)
            (get @cache id)))


(def pacientes (->Cache (atom {}) carrega-paciente))


(println pacientes)

(carrega! pacientes 15)
(carrega! pacientes 30)
(carrega! pacientes 15)
(println pacientes)