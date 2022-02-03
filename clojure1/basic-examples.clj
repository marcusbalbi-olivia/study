(class 50n)

(defn valor-descontado
    "Retornar o valor com desconto de 10%"
    [valor-bruto]
    (let [taxa-desconto (/ 10 100)
        desconto        (* valor-bruto taxa-desconto)]
        (println "Calculando desconto de " desconto)
        (- valor-bruto desconto)))