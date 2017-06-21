(ns banking.service.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :as response]))

(defroutes app-routes
  (GET "/" [] (-> (response/resource-response "public/banking-app.html")
                  (response/content-type "text/html"))))

(defroutes resource-routes
  (GET "/js/banking-app.js" []
       (if-let [app-js (response/resource-response "public/js/dev/banking-app.js"#_(get env :js-app-location))]
         app-js
         (response/response
          "The app JS files are missing. Please compile them with cljsbuild or figwheel."))))
