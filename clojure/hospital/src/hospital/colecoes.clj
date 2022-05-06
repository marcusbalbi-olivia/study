(ns hospital.colecoes
  (:use [clojure pprint]))

;(defn testa-vetor []
;  (let [espera [111 222]]
;  (println espera)
;  (println (conj espera 333))
;  (println (pop espera))
;  (println (conj espera 444))))
;
;
;(testa-vetor)


;(defn testa-lista []
;  (let [espera '(111 222)]
;    (println espera)
;    (println (conj espera 333))
;    (println (pop espera))
;    (println (conj espera 444))))
;
;
;(testa-lista)


;(defn testa-conjunto []
;  (let [espera #{111 222}]
;    (println espera)
;    (println (conj espera 333))
;    ; (println (pop espera)) pop nao funciona pq nao tem ordem
;    (println (conj espera 444))))
;
;
;(testa-conjunto)



;(defn testa-conjunto []
;  (let [espera #{111 222}]                                  ; conjunto nao add dois elementos identicos
;    (println espera)
;    (println (conj espera 333))
;    ; (println (pop espera)) pop nao funciona pq nao tem ordem
;    (println (conj espera 444))))
;
;(testa-conjunto)

(defn testa-fila []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
    (pprint  espera)
    (println (seq (conj espera 333)))
    (println (seq (pop espera)))
    (println (peek espera))
    (println (seq (conj espera 444)))))

(testa-fila)