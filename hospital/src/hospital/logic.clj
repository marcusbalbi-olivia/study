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

(defn chega-em-pausado
  "funcao parece ser pura mas usa random"
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

(defn chega-em-pausado-logando
  "mesma funcao que chega-em-pausado porem com log"
  [hospital departamento pessoa]
  (println "tentando adicionar a pessoa " pessoa)
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (println "Pessoa adicionada" pessoa)
        (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia!" { :tentando-adicionar pessoa }))))

(defn atende [hospital departamento]
  (update hospital departamento pop))

(defn proxima
  "Retorna o Próximo paciente da fila"
  [hospital departamento]
  (peek (get hospital departamento)))

(defn transfere
  "Transfere o proximo paciente da fila de para a fila para"
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (atende de)
        (chega-em para pessoa))))