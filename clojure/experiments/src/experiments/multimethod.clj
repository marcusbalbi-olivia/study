(ns experiments.multimethod)


(defmulti area :Shape)
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})
(defn circle [radius] {:Shape :Circle :radius radius})
(defmethod area :Rect [r]
  (* (:wd r) (:ht r)))
(defmethod area :Circle [c]
  (* (. Math PI) (* (:radius c) (:radius c))))
(defmethod area :default [x] :oops)
(def r (rect 4 13))
(def c (circle 12))
(area r)
-> 52
(area c)
-> 452.3893421169302
(area {})

(defmulti quack :Duck)
(defn rubber-duck [name] {:Duck :RubberDuck :name name})
(defn mallard-duck [name] {:Duck :Mallard :name name})

(defmethod quack :RubberDuck [d]
  (str "I am a rubberduck nosound" (:name d)))

(defmethod quack :Mallard [d]
  (str "I am a mallard duck quaaaaaaaack" (:name d)))


(let [duck (rubber-duck "Donald")]
  (quack duck))

(defn transportadora-selector [item]
  (if (<= (:peso item) 100) :Sedex :BalbiTransport))

(transportadora-selector {:peso 10})
(transportadora-selector {:peso 1000})

(defmulti transportadora transportadora-selector)

(defmethod transportadora :BalbiTransport [item]
  {:preco 10 :item item :transportadora :BalbiTransport})

(defmethod transportadora :Sedex [item]
  {:preco (* (:peso item) 2) :item item :transportadora :Sedex})

(let [item {:peso 10}]
  (transportadora item))