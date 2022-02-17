(ns hospital.logic)

(defn cabe-na-fila? [hospital departamento]
  (-> hospital
      (get ,,, departamento)
      count,,,
      (<,,, 5)))

;(defn cheio2? [hospital departamento]
;  (let [fila (get hospital departamento)
;        tamanho-atual (count fila)]
;    (>= tamanho-atual 5)))

(defn chega-em [hospital departamento pessoa]
    (if (cabe-na-fila? hospital departamento)
      (update hospital departamento conj pessoa)
      (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

; funcao parece ser pura mas usa random
(defn chega-em-pausado [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

; mesma funcao que chega-em-pausado porem com log
(defn chega-em-pausado-logando [hospital departamento pessoa]
  (println "tentando adicionar a pessoa " pessoa)
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (println "Pessoa adicionada" pessoa)
        (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

(defn atende [hospital departamento]
  (update hospital departamento pop))