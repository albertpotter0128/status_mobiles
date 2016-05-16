(ns syng-im.components.react
  (:require [reagent.core :as r]
            [syng-im.components.styles :as st]))

(set! js/window.React (js/require "react-native"))

(def app-registry (.-AppRegistry js/React))
(def navigator (r/adapt-react-class (.-Navigator js/React)))
(def text (r/adapt-react-class (.-Text js/React)))
(def view (r/adapt-react-class (.-View js/React)))
(def image (r/adapt-react-class (.-Image js/React)))
(def touchable-highlight-class (r/adapt-react-class (.-TouchableHighlight js/React)))
(defn touchable-highlight [props content]
  [touchable-highlight-class
   (merge {:underlay-color :transparent} props)
   content])
(def toolbar-android (r/adapt-react-class (.-ToolbarAndroid js/React)))
(def list-view (r/adapt-react-class (.-ListView js/React)))
(def scroll-view (r/adapt-react-class (.-ScrollView js/React)))
(def touchable-without-feedback (r/adapt-react-class (.-TouchableWithoutFeedback js/React)))
(def text-input-class (r/adapt-react-class (.-TextInput js/React)))
(defn text-input [props text]
  [text-input-class (merge
                      {:underlineColorAndroid :transparent
                       :placeholderTextColor  st/text2-color
                       :placeholder           "Type"}
                      props)
   text])
(def drawer-layout-android (r/adapt-react-class (.-DrawerLayoutAndroid js/React)))
(def touchable-opacity (r/adapt-react-class (.-TouchableOpacity js/React)))


(defn icon [n style]
  [image {:source {:uri (keyword (str "icon_" (name n)))}
          :style  style}])

(def platform (.. js/React -Platform -OS))

(def android? (= platform "android"))

(defn list-item [component]
  (r/as-element component))

(def dismiss-keyboard! (js/require "dismissKeyboard"))
