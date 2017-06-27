(defproject banking "0.1.0-SNAPSHOT"
  :dependencies [;; service dependencies
                 [org.clojure/clojure "1.8.0"]
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.0"]
                 [mount "0.1.11"]
                 [http-kit "2.2.0"]
                 [environ "1.1.0"]

                 ;; client dependencies
                 [org.clojure/clojurescript "1.9.562"]
                 [reagent "0.6.2"]
                 [re-frame "0.9.4"]
                 [binaryage/devtools "0.9.4"]
                 [kibu/pushy "0.3.7"]
                 [bidi "2.1.1"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-doo "0.1.7"]
            [lein-environ "1.1.0"]
            [lein-figwheel "0.5.10"]]

  :main banking.service.core

  :profiles {:dev {:env {:asset-path "js/dev"}
                   :dependencies [;; client
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :cljsbuild
                   {:builds {:dev {:figwheel {:on-jsload "banking.client.core/reload-hook"}
                                   :compiler {:main "banking.client.core"
                                              :asset-path "js/dev"
                                              :optimizations :none
                                              :source-map true
                                              :source-map-timestamp true
                                              :preloads [devtools.preload]
                                              :external-config
                                              {:devtools/config {:features-to-install [:formatters :hints]
                                                                 :print-config-overrides true}}}}}}}
             :test {:dependencies []}
             :prod {:env {:asset-path "js/prod"}
                    :dependencies []}
             :uberjar [:prod
                       {:aot :all
                        :prep-tasks ["compile" ["cljsbuild" "once" "release"]]}]}

  :target-path "target/%s"

  :clean-targets ^{:protect false} ["resources/public/js"]

  :cljsbuild {:builds {:dev {:source-paths ["src/banking/client"]
                             :compiler {:output-dir "resources/public/js/dev"
                                        :output-to "resources/public/js/dev/banking-app.js"}}

                       :release {:source-paths ["src/banking/client"]
                                 :compiler {:asset-path "/js/prod"
                                            :optimizations :advanced
                                            :pretty-print false
                                            :verbose true
                                            :output-dir "resources/public/js/prod"
                                            :output-to "resources/public/js/prod/banking-app.js"}}}})
