(ns estoque.aula3)
(defn maior-que-100?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retornar o valor com desconto de 10%"
  [aplica?, valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-desconto (/ 10 100)
          desconto (* valor-bruto taxa-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado maior-que-100?, 500))
(println (valor-descontado maior-que-100?, 90))

;; funcao anonima
(println (valor-descontado (fn [v] (> v 50)) 60))
(println (valor-descontado #(> %1 30) 100))
(println (valor-descontado #(> % 30) 100))
