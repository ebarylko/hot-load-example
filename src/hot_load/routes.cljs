(ns ^:dev/always hot-load.routes
  (:require
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [reitit.coercion.spec :as rss]
   [reitit.frontend :as rf]
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [hot-load.views :as views]
   [hot-load.events :as events]))


(def routes
  [
   ["/" {:name      :home
        :view      views/home-panel
        :link-text "Home"}]])


(defn on-navigate [new-match]
  (when new-match
    :current-route
    (re-frame/dispatch [:navigated new-match])))

;; events


(re-frame/reg-event-db
 :navigated
 (fn [db [_ new-match]]
   (let [old-match   (:current-route db)
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     (assoc db :current-route (assoc new-match :controllers controllers)))))



(re-frame/reg-fx
 :push-state
 (fn [route]
   (apply rfe/push-state route)))

;; subscriptions
(re-frame/reg-sub
 :current-route
 (fn [db]
   (:current-route db)))

(def router
  (rf/router
   routes
   {:data {:coercion rss/coercion}}))

(defonce history (atom nil))

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
   router
   on-navigate
   {:use-fragment true}))

(defn router-component [{:keys [router]}]
  (let [current-route @(re-frame/subscribe [:current-route])]
    (println "At the router component " current-route)
    [:div.main-container
     [views/top-menu {:router router :current-route current-route}]
     (when current-route
       [(-> current-route :data :view )])]))
