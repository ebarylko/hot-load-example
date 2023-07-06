(ns hot-load.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [hot-load.events :as events]
   [hot-load.views :as views]
   [hot-load.config :as config]
   [hot-load.routes :as routes]
   ["react-dom/client" :refer [createRoot]]
   [reagent.core :as r]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defonce root (createRoot (.getElementById js/document "app")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (println "We are now rendering the app")
  (.render root (r/as-element [routes/router-component {:router routes/router}])))



(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (routes/init-routes!)
  (mount-root))


