(ns banking.service.compojure-api.handler
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [ring.middleware.defaults :as defaults]))

(c/defroutes app-routes
  (c/GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (defaults/wrap-defaults app-routes defaults/site-defaults))
