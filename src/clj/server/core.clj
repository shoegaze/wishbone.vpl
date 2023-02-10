(ns server.core
  (:require [ring.adapter.jetty :as jetty]
            [server.http.router :as router]
            [server.runner.core :as runner]
            [server.head.core :as head]))


; TODO: Arguments:
;   * :port port                  ... Sets the server port
;   * :headless flag              ... Starts the server in headless mode (No render output)
;   * :resolution [width height]  ... Sets the output resolution
;   * :tick-rate                  ... Sets the simulation tick rate
(defn -main [& _args]
  ; Language runner
  (println "Starting language runner ...")
  (runner/start-runner)

  ; Head
  (println "Starting head ...")
  (head/start-head)

  ; HTTP server
  (let [host "localhost"
        port 8886
        url  (str "http://" host ":" port)]
    (println "Starting HTTP server at" url "...")
    (jetty/run-jetty router/site {:host host
                                  :port port})))