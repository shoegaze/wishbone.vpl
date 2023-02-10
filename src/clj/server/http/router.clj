(ns server.http.router
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer (content-type, resource-response)]
            [ring.middleware.defaults :refer :all]))


(defn- html-index []
  (content-type
    (resource-response "index.dev.html" {:root "public"})
    "text/html"))

(defroutes handler
           (GET "/" [] (html-index))
           (route/not-found "404"))

(def site
  (wrap-defaults handler site-defaults))