(ns take_home_engineering_challenge.components.webserver
  (:require [ring.middleware.json :as json]
            [ring.adapter.jetty :as jetty]
            [ring.logger :as logger]
            [take_home_engineering_challenge.webserver.app_routes :refer [app-routes]]
            [take_home_engineering_challenge.services.config :refer [gather-configs]]
))

(def handler
  (-> app-routes
      json/wrap-json-response
      logger/wrap-with-logger
))

(defonce web-server (jetty/run-jetty handler {:port (:port gather-configs) :join? false}))