(ns banking.client.core
  "Core client namespace for the banking app. Responsible for
  providing functions that initialize the app on startup"
  (:require [reagent.core :as reagent]))

(defn app-view
  "Root-level view for the app"
  []
  [:div
   [:h1 "Hello World!"]])

(defn ^:export init
  "Entry point into the app.

  All app initialization logic should be kicked off here"
  []
  (reagent/render [app-view]
                  (.getElementById js/document "app")))

(defn ^:export reload-hook
  "Reload hook used by figwheel"
  []
  (reagent/render [app-view]
                  (.getElementById js/document "app")))
