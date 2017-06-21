(ns banking.service.handler
  (:require [compojure.core :refer [routes]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [banking.service.routes :as routes]))

(def app
  (-> (routes routes/app-routes
              routes/resource-routes)
      (wrap-defaults site-defaults)))
