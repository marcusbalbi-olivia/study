(ns estoque.outro)

(defn imprime-mensagem
  [msg]
  (println msg))

(if (> 10 5) (imprime-mensagem "10 é maior que 5"))