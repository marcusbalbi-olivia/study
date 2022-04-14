(ns hospital2.logic
  (:require [hospital2.model :as h.model]))


(defn agora "Hora atual em milisegundos" []
  (h.model/to-ms (java.util.Date.)))