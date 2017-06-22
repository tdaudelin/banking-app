(ns banking.service.conf
  (:require [environ.core :refer [env]]
            [mount.core :as mount :refer [defstate]]))

(defstate config
  :start
  (merge {}
         (select-keys env
                      [:asset-path])))
