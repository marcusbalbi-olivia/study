(defn valor-descontado
    "Retornar o valor com desconto de 10%"
    [valor-bruto]
    (let [taxa-desconto (/ 10 100)
        desconto        (* valor-bruto taxa-desconto)]
        (println "Calculando desconto de " desconto)
        (- valor-bruto desconto)))


(if (> 500 100) 
(println "500 e maior que 100")
(println "500 não é maior que 100"))

(defn eh-maior
    "Verifica se o primeiro numero e maior que o segundo"
    [n1, n2]
    (if (> n1, n2)
        (println "O número " n1 "é maior do que " n2)
        (println "O número " n1 "não é maior do que " n2)))


(defn valor-descontado
    "Retornar o valor com desconto de 10%"
    [valor-bruto]
    (if (> valor-bruto 100)
        (let [taxa-desconto (/ 10 100)
            desconto        (* valor-bruto taxa-desconto)]
            (println "Calculando desconto de " desconto)
            (- valor-bruto desconto))
        valor-bruto))


(class 50)
(class "50")
(class 50.99)

(if nil (println "true") (println "false"))