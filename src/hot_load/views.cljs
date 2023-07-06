(ns hot-load.views
  (:require
   [re-frame.core :as re-frame]
   [hot-load.styles :as styles]
   [hot-load.subs :as subs]))


(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      {:class (styles/level1)}
      "Hello from Hanna" @name]
     [:h1 "How are you doing?"]]))

(defn top-menu [{:keys [router current-route]}]
  [:div 
   [:nav {:class "navbar", :role "navigation", :aria-label "main navigation"}
    [:div {:class "navbar-brand"}
     [:h1.navbar-item.is-size-5.has-text-weight-bold "My app"]
     [:a.navbar-item.is-size-5.has-text-weight-bold {:on-click  #(re-frame/dispatch [::push-state :home])} "Home"]
     [:a.navbar-item.is-size-5.has-text-weight-bold {:on-click  #(re-frame/dispatch [::push-state :about])} "About"]
     [:a {:role "button", :class "navbar-burger", :aria-label "menu", :aria-expanded "false", :data-target "navbarBasicExample"}
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]]]
    [:div {:id "navbarBasicExample", :class "navbar-menu"}
     [:div {:class "navbar-start"}
      [:a {:class "navbar-item"} "Documentation"]
      [:div {:class "navbar-item has-dropdown is-hoverable"}
       [:a {:class "navbar-link"} "More"]
       [:div {:class "navbar-dropdown"}
        [:a {:class "navbar-item"} "Item 1"]
        [:a {:class "navbar-item"} "Item 2"]
        [:a {:class "navbar-item"} "Item 3"]
        [:hr {:class "navbar-divider"}]
        [:a {:class "navbar-item"} "Report an issue"]]]]
     [:div {:class "navbar-end"}
      [:div {:class "navbar-item"}]]]]])
