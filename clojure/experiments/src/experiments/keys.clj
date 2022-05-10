(ns experiments.keys)

(def settings {:url "http://test.com"
              :port "8080" })

(keys settings)
(vals settings)

(select-keys settings [:url])

(map key settings)