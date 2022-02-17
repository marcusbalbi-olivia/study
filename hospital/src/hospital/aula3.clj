(ns hospital.aula3
  (:use  [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))


; Simbolo Global que todas thread pode ter acesso
(def nome "Balbi")


; novo binding atribuindo outro tipo threads que leem esse valor podem quebrar
(def nome 212312)


(let [nome "Balbi"]
  ; coisa 1
  ; coisa 2
  ; coisa 3
  (println nome)
  ; nao reatribui o simbolo do bloco superior
  ; cria um novo bloco com um novo simbolo nome e esconde de escopo superior
  ; shadowing
  (let [nome "Marcus"]
    ; coisa 3
    ; coisa 4
    (println nome)
    )
  (println nome)
  )

(defn teste-atomao []
  (let [hospital-balbi (atom { :espera h.model/fila_vazia })]
    (println hospital-balbi)
    (pprint hospital-balbi)
    (pprint (deref hospital-balbi))
    (pprint @hospital-balbi)
    ; nao e assim que altera um valor de um atom
    (pprint (assoc @hospital-balbi :laboratorio1 h.model/fila_vazia))
    (pprint @hospital-balbi)

    ; uma das maneiras de alterar o valor de um atomo
    (swap! hospital-balbi assoc :laboratorio1 h.model/fila_vazia)
    (pprint @hospital-balbi)

    (swap! hospital-balbi assoc :laboratorio2 h.model/fila_vazia)
    (pprint @hospital-balbi)

    (pprint (update @hospital-balbi :laboratorio1 conj "22222"))
    (pprint @hospital-balbi)

    ; indo para swap
    (swap! hospital-balbi update :laboratorio1 conj "1111")
    (pprint hospital-balbi)

    )
  )


(teste-atomao)