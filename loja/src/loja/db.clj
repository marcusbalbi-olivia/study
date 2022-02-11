(ns loja.db)

(def pedido1 {
              :usuario 15
              :itens {
                      :mochila { :id :mochila :quantidade 2, :preco-unitario 80 }
                      :camiseta { :id :camiseta :quantidade 2, :preco-unitario 40 }
                      :tenis { :id :tenis :quantidade 1 }
                      }
              })

(def pedido2 {
              :usuario 18
              :itens {
                      :mochila { :id :mochila :quantidade 1, :preco-unitario 80 }
                      :camiseta { :id :camiseta :quantidade 1, :preco-unitario 40 }
                      }
              })

(def pedido3 {
              :usuario 20
              :itens {
                      :mochila { :id :mochila :quantidade 1, :preco-unitario 80 }
                      :camiseta { :id :camiseta :quantidade 6, :preco-unitario 40 }
                      :tenis { :id :tenis :quantidade 1 }
                      }
              })

(def pedido4 {
              :usuario 22
              :itens {
                      :mochila { :id :mochila :quantidade 3, :preco-unitario 80 }
                      :camiseta { :id :camiseta :quantidade 1, :preco-unitario 40 }
                      :tenis { :id :tenis :quantidade 1 }
                      }
              })

(def pedido5 {
              :usuario 15
              :itens {
                      :mochila { :id :mochila :quantidade 10, :preco-unitario 80 }
                      :camiseta { :id :camiseta :quantidade 15, :preco-unitario 40 }
                      }
              })

(defn todos-os-pedidos []
  [pedido1 pedido2 pedido3 pedido4 pedido5])