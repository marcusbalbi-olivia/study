(ns loja.aula1)

; ["Daniela", "Joao", "Pedro"]
; {"balbi" 32, "joana" 21}
; '(1 2 3 4 5)
; [[0 1]]
; #{}

; map
; reduce
; filter
; for

(map println ["Daniela", "Joao", "Pedro"])
(println (first ["daniela", "Pedro", "Jonas", "Gabriel"]))
(println (rest ["daniela", "Pedro", "Jonas", "Gabriel"]))
(println (next ["danie la", "Pedro", "Jonas", "Gabriel"]))

(println (rest []))
(println (next []))


(println (seq []))
(println (seq [1 2 3 4 5]))

(println "\n\n\n Tratabalhando com meu mapa \n\n\n")
;(defn  meu-mapa
;  [funcao sequencia]
;  (let [primeiro (first sequencia)]
;    (funcao primeiro)
;    (meu-mapa funcao (next sequencia))))

;(meu-mapa println ["Joao"])


;(defn  meu-mapa
;    [funcao sequencia]
;    (let [primeiro (first sequencia)]
;      (if primeiro
;        (do
;          (funcao primeiro)
;          (meu-mapa funcao (rest sequencia))))))
;
;(meu-mapa println ["Joao", "Nelio", "Batista"])
;(meu-mapa println ["Joao", false, "Batista"])


(defn  meu-mapa
    [funcao sequencia]
    (let [primeiro (first sequencia)]
      (if (not (nil? primeiro))
        (do
          (funcao primeiro)
          (meu-mapa funcao (rest sequencia))))))


(meu-mapa println ["Joao", false, "Batista"])