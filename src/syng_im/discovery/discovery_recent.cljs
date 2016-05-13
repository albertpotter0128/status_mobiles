(ns syng-im.discovery.discovery-recent
  (:require
    [re-frame.core :refer [subscribe]]
    [syng-im.components.react :refer [view list-view list-item]]
    [syng-im.utils.listview :refer [to-realm-datasource to-datasource2]]
    [syng-im.discovery.styles :as st]
    [syng-im.discovery.discovery-popular-list-item
     :refer [discovery-popular-list-item]]))

(defn render-row [row _ _]
  (list-item [discovery-popular-list-item row]))

(defn render-separator [_ row-id _]
  (list-item [view {:style st/row-separator
                    :key   row-id}]))

(defn discovery-recent []
  (let [discoveries (subscribe [:get :discoveries])]
    (fn []
      [list-view {:dataSource      (to-datasource2 @discoveries)
                  :renderRow       render-row
                  :renderSeparator render-separator
                  :style           st/recent-list}])))
