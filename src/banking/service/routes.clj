(ns banking.service.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :as response]
            [banking.service.conf :refer [config]]))

(defroutes app-routes
  (GET "/" [] (-> (response/resource-response "public/banking-app.html")
                  (response/content-type "text/html"))))

(defroutes resource-routes
  (GET "/js/banking-app.js" []
       (let [app-location (str "public/" (get config :asset-path) "/banking-app.js")]
         (if-let [app-js (response/resource-response app-location)]
           app-js
           (response/response
            (str "The expected app JS files at " app-location
                 " are missing. Please compile them with cljsbuild or figwheel"))))))
