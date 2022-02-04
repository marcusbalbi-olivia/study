(ns estoque.aula4)

(def precos [30 700 1000])

(println precos)
(println (precos 0))
(println (get precos 1))
(println (get precos 80))  ;;return nil
(println (get precos 80 0))  ;;return default value
(println (get precos 2 0))  ;;return value if exists

(println (conj precos 5))
(println precos)

(println (inc 5))
(println (update precos 0 inc))
(println (update precos 1 dec))
(println precos)

(defn soma-3
  [x]
  (println "Estou somando 3 ao valor " x)
  (+ x 3))
(println (update precos 2 soma-3))

(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retornar o valor com desconto de 10%"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-desconto (/ 10 100)
          desconto (* valor-bruto taxa-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (map valor-descontado precos))

(println (range 10))
(println (filter even? (range 10)))

(println (filter aplica-desconto? precos))

(println (map valor-descontado (filter aplica-desconto? precos)))

(println (reduce + precos))

(defn minha-soma
  [x, y]
  (println "somando valores" x, y)
  (+ x y))

(println (reduce minha-soma precos))
(println (reduce minha-soma (range 10)))

(println (reduce minha-soma 0 precos))
(println (reduce minha-soma 500 (range 10)))


