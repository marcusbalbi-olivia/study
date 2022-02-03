(ns estoque.aula3)


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

(println (valor-descontado 500))
(println (valor-descontado 90))
(println (aplica-desconto? 150))
(println (aplica-desconto? 80))