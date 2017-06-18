(defproject banking "0.1.0-SNAPSHOT"
  :dependencies [;; service dependencies
                 [org.clojure/clojure "1.8.0"]
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.0"]

                 ;; client dependencies
                 [org.clojure/clojurescript "1.9.562"]
                 [reagent "0.6.2"]
                 [re-frame "0.9.4"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-figwheel "0.5.10"]
            [lein-doo "0.1.7"]
            [lein-ring "0.9.7"]]

  :profiles {:dev {:dependencies [;; service
                                  [javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]

                                  ;; client
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :cljsbuild
                   {:builds {:client {:figwheel {:on-jsload "banking.client.core/reload-hook"}
                                      :compiler {:main "banking.client.core"
                                                 :asset-path "js"
                                                 :optimizations :none
                                                 :source-map true
                                                 :source-map-timestamp true}}}}}
             :test [{:dependencies []}]
             :prod {:dependencies []}
             :uberjar [:prod
                       {:aot :all}]}

  :target-path "target/%s"

  :ring {:handler banking.service.compojure-api.handler/app}

  :clean-targets ^{:protect false} ["resources/public/js"]

  :cljsbuild {:builds {:client {:source-paths ["src/banking/client"]
                                :compiler {:output-dir "resources/public/js"
                                           :output-to "resources/public/js/banking-app.js"}}}})
