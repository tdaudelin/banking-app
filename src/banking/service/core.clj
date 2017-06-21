(ns banking.service.core
  (:gen-class)
  (:require [mount.core :as mount :refer [defstate]]
            [org.httpkit.server :as http-kit]
            [banking.service.compojure-api.handler :as handler]))

(defn start-server!
  [port]
  (do (println "starting backend service on port" port)
      (http-kit/run-server #'handler/app {:port port})))

(defn stop-server!
  [server]
  (do (println "stopping server")
      (server :timeout 100)))

(defstate server
  :start (start-server! (get (mount/args) :port))
  :stop (stop-server! server))

(defn dev-main
  [port]
  (-> (mount/with-args {:port port})
      (mount/start)))

(defn -main
  []
  (let [port 8080]
    (mount/start-with-args {:port port})))
