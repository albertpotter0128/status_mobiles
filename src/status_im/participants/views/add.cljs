(ns status-im.participants.views.add
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [re-frame.core :refer [subscribe dispatch]]
            [status-im.resources :as res]
            [status-im.components.react :refer [view list-view list-item]]
            [status-im.components.status-bar :refer [status-bar]]
            [status-im.components.toolbar :refer [toolbar]]
            [status-im.utils.listview :refer [to-datasource]]
            [status-im.participants.views.contact :refer [participant-contact]]
            [reagent.core :as r]
            [status-im.participants.styles :as st]
            [status-im.i18n :refer [label]]
            [status-im.components.styles :as cst]))

(defn new-participants-toolbar [platform-specific]
  [view
   [status-bar {:platform-specific platform-specific}]
   [toolbar
    {:title  (label :t/add-participants)
     :action {:image   {:source res/v                       ;; {:uri "icon_search"}
                        :style  st/new-participant-image}
              :handler #(do (dispatch [:add-new-participants])
                            (dispatch [:navigate-back]))}}]])

(defn new-participants-row
  [row _ _]
  (list-item [participant-contact row]))

(defview new-participants [{platform-specific :platform-specific}]
  [contacts [:all-new-contacts]]
  [view st/participants-container
   [new-participants-toolbar platform-specific]
   [list-view {:dataSource (to-datasource contacts)
               :renderRow  new-participants-row
               :style      st/participants-list}]])
