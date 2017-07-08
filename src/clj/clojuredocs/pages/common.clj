(ns clojuredocs.pages.common
  (:require [clojure.string :as str]
            [clojuredocs.util :as util]
            [clojuredocs.config :as config]
            [clojuredocs.env :as env]
            [clojuredocs.github :as gh]
            [clojuredocs.search :as search]))

(defn gh-auth-url [& [redirect-to-after-auth-url]]
  (let [redirect-url (str "/gh-callback" redirect-to-after-auth-url)]
    (gh/auth-redirect-url
      (merge config/gh-creds
             {:redirect-uri (config/url redirect-url)}))))


(defn $ga-script-tag [ga-tracking-id]
  (when ga-tracking-id
    [:script "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', '" ga-tracking-id "', 'auto');
  ga('send', 'pageview');"]))

(defn $new-relic-script-tag []
  (when (and config/new-relic-browser-id
             config/new-relic-browser-key)
    [:script {:type "text/javascript"}
     (str "window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o?o:e)},o,o.exports)}return e[n].exports}if(\"function\"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({QJf3ax:[function(t,e){function n(t){function e(e,n,a){t&&t(e,n,a),a||(a={});for(var c=s(e),f=c.length,u=i(a,o,r),d=0;f>d;d++)c[d].apply(u,n);return u}function a(t,e){f[t]=s(t).concat(e)}function s(t){return f[t]||[]}function c(){return n(e)}var f={};return{on:a,emit:e,create:c,listeners:s,_events:f}}function r(){return{}}var o=\"nr@context\",i=t(\"gos\");e.exports=n()},{gos:\"7eSDFh\"}],ee:[function(t,e){e.exports=t(\"QJf3ax\")},{}],3:[function(t){function e(t,e,n,i,s){try{c?c-=1:r(\"err\",[s||new UncaughtException(t,e,n)])}catch(f){try{r(\"ierr\",[f,(new Date).getTime(),!0])}catch(u){}}return\"function\"==typeof a?a.apply(this,o(arguments)):!1}function UncaughtException(t,e,n){this.message=t||\"Uncaught error with no additional information\",this.sourceURL=e,this.line=n}function n(t){r(\"err\",[t,(new Date).getTime()])}var r=t(\"handle\"),o=t(5),i=t(\"ee\"),a=window.onerror,s=!1,c=0;t(\"loader\").features.err=!0,window.onerror=e,NREUM.noticeError=n;try{throw new Error}catch(f){\"stack\"in f&&(t(1),t(4),\"addEventListener\"in window&&t(2),window.XMLHttpRequest&&XMLHttpRequest.prototype&&XMLHttpRequest.prototype.addEventListener&&t(3),s=!0)}i.on(\"fn-start\",function(){s&&(c+=1)}),i.on(\"fn-err\",function(t,e,r){s&&(this.thrown=!0,n(r))}),i.on(\"fn-end\",function(){s&&!this.thrown&&c>0&&(c-=1)}),i.on(\"internal-error\",function(t){r(\"ierr\",[t,(new Date).getTime(),!0])})},{1:8,2:5,3:9,4:7,5:20,ee:\"QJf3ax\",handle:\"D5DuLP\",loader:\"G9z0Bl\"}],4:[function(t){function e(){}if(window.performance&&window.performance.timing&&window.performance.getEntriesByType){var n=t(\"ee\"),r=t(\"handle\"),o=t(2);t(\"loader\").features.stn=!0,t(1),n.on(\"fn-start\",function(t){var e=t[0];e instanceof Event&&(this.bstStart=Date.now())}),n.on(\"fn-end\",function(t,e){var n=t[0];n instanceof Event&&r(\"bst\",[n,e,this.bstStart,Date.now()])}),o.on(\"fn-start\",function(t,e,n){this.bstStart=Date.now(),this.bstType=n}),o.on(\"fn-end\",function(t,e){r(\"bstTimer\",[e,this.bstStart,Date.now(),this.bstType])}),n.on(\"pushState-start\",function(){this.time=Date.now(),this.startPath=location.pathname+location.hash}),n.on(\"pushState-end\",function(){r(\"bstHist\",[location.pathname+location.hash,this.startPath,this.time])}),\"addEventListener\"in window.performance&&(window.performance.addEventListener(\"webkitresourcetimingbufferfull\",function(){r(\"bstResource\",[window.performance.getEntriesByType(\"resource\")]),window.performance.webkitClearResourceTimings()},!1),window.performance.addEventListener(\"resourcetimingbufferfull\",function(){r(\"bstResource\",[window.performance.getEntriesByType(\"resource\")]),window.performance.clearResourceTimings()},!1)),document.addEventListener(\"scroll\",e,!1),document.addEventListener(\"keypress\",e,!1),document.addEventListener(\"click\",e,!1)}},{1:6,2:8,ee:\"QJf3ax\",handle:\"D5DuLP\",loader:\"G9z0Bl\"}],5:[function(t,e){function n(t){i.inPlace(t,[\"addEventListener\",\"removeEventListener\"],\"-\",r)}function r(t){return t[1]}var o=(t(1),t(\"ee\").create()),i=t(2)(o),a=t(\"gos\");if(e.exports=o,n(window),\"getPrototypeOf\"in Object){for(var s=document;s&&!s.hasOwnProperty(\"addEventListener\");)s=Object.getPrototypeOf(s);s&&n(s);for(var c=XMLHttpRequest.prototype;c&&!c.hasOwnProperty(\"addEventListener\");)c=Object.getPrototypeOf(c);c&&n(c)}else XMLHttpRequest.prototype.hasOwnProperty(\"addEventListener\")&&n(XMLHttpRequest.prototype);o.on(\"addEventListener-start\",function(t){if(t[1]){var e=t[1];\"function\"==typeof e?this.wrapped=t[1]=a(e,\"nr@wrapped\",function(){return i(e,\"fn-\",null,e.name||\"anonymous\")}):\"function\"==typeof e.handleEvent&&i.inPlace(e,[\"handleEvent\"],\"fn-\")}}),o.on(\"removeEventListener-start\",function(t){var e=this.wrapped;e&&(t[1]=e)})},{1:20,2:21,ee:\"QJf3ax\",gos:\"7eSDFh\"}],6:[function(t,e){var n=(t(2),t(\"ee\").create()),r=t(1)(n);e.exports=n,r.inPlace(window.history,[\"pushState\"],\"-\")},{1:21,2:20,ee:\"QJf3ax\"}],7:[function(t,e){var n=(t(2),t(\"ee\").create()),r=t(1)(n);e.exports=n,r.inPlace(window,[\"requestAnimationFrame\",\"mozRequestAnimationFrame\",\"webkitRequestAnimationFrame\",\"msRequestAnimationFrame\"],\"raf-\"),n.on(\"raf-start\",function(t){t[0]=r(t[0],\"fn-\")})},{1:21,2:20,ee:\"QJf3ax\"}],8:[function(t,e){function n(t,e,n){var r=t[0];\"string\"==typeof r&&(r=new Function(r)),t[0]=o(r,\"fn-\",null,n)}var r=(t(2),t(\"ee\").create()),o=t(1)(r);e.exports=r,o.inPlace(window,[\"setTimeout\",\"setInterval\",\"setImmediate\"],\"setTimer-\"),r.on(\"setTimer-start\",n)},{1:21,2:20,ee:\"QJf3ax\"}],9:[function(t,e){function n(){c.inPlace(this,d,\"fn-\")}function r(t,e){c.inPlace(e,[\"onreadystatechange\"],\"fn-\")}function o(t,e){return e}var i=t(\"ee\").create(),a=t(1),s=t(2),c=s(i),f=s(a),u=window.XMLHttpRequest,d=[\"onload\",\"onerror\",\"onabort\",\"onloadstart\",\"onloadend\",\"onprogress\",\"ontimeout\"];e.exports=i,window.XMLHttpRequest=function(t){var e=new u(t);try{i.emit(\"new-xhr\",[],e),f.inPlace(e,[\"addEventListener\",\"removeEventListener\"],\"-\",function(t,e){return e}),e.addEventListener(\"readystatechange\",n,!1)}catch(r){try{i.emit(\"internal-error\",[r])}catch(o){}}return e},window.XMLHttpRequest.prototype=u.prototype,c.inPlace(XMLHttpRequest.prototype,[\"open\",\"send\"],\"-xhr-\",o),i.on(\"send-xhr-start\",r),i.on(\"open-xhr-start\",r)},{1:5,2:21,ee:\"QJf3ax\"}],10:[function(t){function e(t){if(\"string\"==typeof t&&t.length)return t.length;if(\"object\"!=typeof t)return void 0;if(\"undefined\"!=typeof ArrayBuffer&&t instanceof ArrayBuffer&&t.byteLength)return t.byteLength;if(\"undefined\"!=typeof Blob&&t instanceof Blob&&t.size)return t.size;if(\"undefined\"!=typeof FormData&&t instanceof FormData)return void 0;try{return JSON.stringify(t).length}catch(e){return void 0}}function n(t){var n=this.params,r=this.metrics;if(!this.ended){this.ended=!0;for(var i=0;c>i;i++)t.removeEventListener(s[i],this.listener,!1);if(!n.aborted){if(r.duration=(new Date).getTime()-this.startTime,4===t.readyState){n.status=t.status;var a=t.responseType,f=\"arraybuffer\"===a||\"blob\"===a||\"json\"===a?t.response:t.responseText,u=e(f);if(u&&(r.rxSize=u),this.sameOrigin){var d=t.getResponseHeader(\"X-NewRelic-App-Data\");d&&(n.cat=d.split(\", \").pop())}}else n.status=0;r.cbTime=this.cbTime,o(\"xhr\",[n,r,this.startTime])}}}function r(t,e){var n=i(e),r=t.params;r.host=n.hostname+\":\"+n.port,r.pathname=n.pathname,t.sameOrigin=n.sameOrigin}if(window.XMLHttpRequest&&XMLHttpRequest.prototype&&XMLHttpRequest.prototype.addEventListener&&!/CriOS/.test(navigator.userAgent)){t(\"loader\").features.xhr=!0;var o=t(\"handle\"),i=t(2),a=t(\"ee\"),s=[\"load\",\"error\",\"abort\",\"timeout\"],c=s.length,f=t(1);t(4),t(3),a.on(\"new-xhr\",function(){this.totalCbs=0,this.called=0,this.cbTime=0,this.end=n,this.ended=!1,this.xhrGuids={}}),a.on(\"open-xhr-start\",function(t){this.params={method:t[0]},r(this,t[1]),this.metrics={}}),a.on(\"open-xhr-end\",function(t,e){\"loader_config\"in NREUM&&\"xpid\"in NREUM.loader_config&&this.sameOrigin&&e.setRequestHeader(\"X-NewRelic-ID\",NREUM.loader_config.xpid)}),a.on(\"send-xhr-start\",function(t,n){var r=this.metrics,o=t[0],i=this;if(r&&o){var f=e(o);f&&(r.txSize=f)}this.startTime=(new Date).getTime(),this.listener=function(t){try{\"abort\"===t.type&&(i.params.aborted=!0),(\"load\"!==t.type||i.called===i.totalCbs&&(i.onloadCalled||\"function\"!=typeof n.onload))&&i.end(n)}catch(e){try{a.emit(\"internal-error\",[e])}catch(r){}}};for(var u=0;c>u;u++)n.addEventListener(s[u],this.listener,!1)}),a.on(\"xhr-cb-time\",function(t,e,n){this.cbTime+=t,e?this.onloadCalled=!0:this.called+=1,this.called!==this.totalCbs||!this.onloadCalled&&\"function\"==typeof n.onload||this.end(n)}),a.on(\"xhr-load-added\",function(t,e){var n=\"\"+f(t)+!!e;this.xhrGuids&&!this.xhrGuids[n]&&(this.xhrGuids[n]=!0,this.totalCbs+=1)}),a.on(\"xhr-load-removed\",function(t,e){var n=\"\"+f(t)+!!e;this.xhrGuids&&this.xhrGuids[n]&&(delete this.xhrGuids[n],this.totalCbs-=1)}),a.on(\"addEventListener-end\",function(t,e){e instanceof XMLHttpRequest&&\"load\"===t[0]&&a.emit(\"xhr-load-added\",[t[1],t[2]],e)}),a.on(\"removeEventListener-end\",function(t,e){e instanceof XMLHttpRequest&&\"load\"===t[0]&&a.emit(\"xhr-load-removed\",[t[1],t[2]],e)}),a.on(\"fn-start\",function(t,e,n){e instanceof XMLHttpRequest&&(\"onload\"===n&&(this.onload=!0),(\"load\"===(t[0]&&t[0].type)||this.onload)&&(this.xhrCbStart=(new Date).getTime()))}),a.on(\"fn-end\",function(t,e){this.xhrCbStart&&a.emit(\"xhr-cb-time\",[(new Date).getTime()-this.xhrCbStart,this.onload,e],e)})}},{1:\"XL7HBI\",2:11,3:9,4:5,ee:\"QJf3ax\",handle:\"D5DuLP\",loader:\"G9z0Bl\"}],11:[function(t,e){e.exports=function(t){var e=document.createElement(\"a\"),n=window.location,r={};e.href=t,r.port=e.port;var o=e.href.split(\"://\");return!r.port&&o[1]&&(r.port=o[1].split(\"/\")[0].split(\":\")[1]),r.port&&\"0\"!==r.port||(r.port=\"https\"===o[0]?\"443\":\"80\"),r.hostname=e.hostname||n.hostname,r.pathname=e.pathname,\"/\"!==r.pathname.charAt(0)&&(r.pathname=\"/\"+r.pathname),r.sameOrigin=!e.hostname||e.hostname===document.domain&&e.port===n.port&&e.protocol===n.protocol,r}},{}],gos:[function(t,e){e.exports=t(\"7eSDFh\")},{}],\"7eSDFh\":[function(t,e){function n(t,e,n){if(r.call(t,e))return t[e];var o=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:o,writable:!0,enumerable:!1}),o}catch(i){}return t[e]=o,o}var r=Object.prototype.hasOwnProperty;e.exports=n},{}],D5DuLP:[function(t,e){function n(t,e,n){return r.listeners(t).length?r.emit(t,e,n):(o[t]||(o[t]=[]),void o[t].push(e))}var r=t(\"ee\").create(),o={};e.exports=n,n.ee=r,r.q=o},{ee:\"QJf3ax\"}],handle:[function(t,e){e.exports=t(\"D5DuLP\")},{}],XL7HBI:[function(t,e){function n(t){var e=typeof t;return!t||\"object\"!==e&&\"function\"!==e?-1:t===window?0:i(t,o,function(){return r++})}var r=1,o=\"nr@id\",i=t(\"gos\");e.exports=n},{gos:\"7eSDFh\"}],id:[function(t,e){e.exports=t(\"XL7HBI\")},{}],loader:[function(t,e){e.exports=t(\"G9z0Bl\")},{}],G9z0Bl:[function(t,e){function n(){var t=p.info=NREUM.info;if(t&&t.agent&&t.licenseKey&&t.applicationID&&c&&c.body){p.proto=\"https\"===d.split(\":\")[0]||t.sslForHttp?\"https://\":\"http://\",a(\"mark\",[\"onload\",i()]);var e=c.createElement(\"script\");e.src=p.proto+t.agent,c.body.appendChild(e)}}function r(){\"complete\"===c.readyState&&o()}function o(){a(\"mark\",[\"domContent\",i()])}function i(){return(new Date).getTime()}var a=t(\"handle\"),s=window,c=s.document,f=\"addEventListener\",u=\"attachEvent\",d=(\"\"+location).split(\"?\")[0],p=e.exports={offset:i(),origin:d,features:{}};c[f]?(c[f](\"DOMContentLoaded\",o,!1),s[f](\"load\",n,!1)):(c[u](\"onreadystatechange\",r),s[u](\"onload\",n)),a(\"mark\",[\"firstbyte\",i()])},{handle:\"D5DuLP\"}],20:[function(t,e){function n(t,e,n){e||(e=0),\"undefined\"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(0>o?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=n},{}],21:[function(t,e){function n(t){return!(t&&\"function\"==typeof t&&t.apply&&!t[i])}var r=t(\"ee\"),o=t(1),i=\"nr@wrapper\",a=Object.prototype.hasOwnProperty;e.exports=function(t){function e(t,e,r,a){function nrWrapper(){var n,i,s,f;try{i=this,n=o(arguments),s=r&&r(n,i)||{}}catch(d){u([d,\"\",[n,i,a],s])}c(e+\"start\",[n,i,a],s);try{return f=t.apply(i,n)}catch(p){throw c(e+\"err\",[n,i,p],s),p}finally{c(e+\"end\",[n,i,f],s)}}return n(t)?t:(e||(e=\"\"),nrWrapper[i]=!0,f(t,nrWrapper),nrWrapper)}function s(t,r,o,i){o||(o=\"\");var a,s,c,f=\"-\"===o.charAt(0);for(c=0;c<r.length;c++)s=r[c],a=t[s],n(a)||(t[s]=e(a,f?s+o:o,i,s,t))}function c(e,n,r){try{t.emit(e,n,r)}catch(o){u([o,e,n,r])}}function f(t,e){if(Object.defineProperty&&Object.keys)try{var n=Object.keys(t);return n.forEach(function(n){Object.defineProperty(e,n,{get:function(){return t[n]},set:function(e){return t[n]=e,e}})}),e}catch(r){u([r])}for(var o in t)a.call(t,o)&&(e[o]=t[o]);return e}function u(e){try{t.emit(\"internal-error\",e)}catch(n){}}return t||(t=r),e.inPlace=s,e.flag=i,e}},{1:20,ee:\"QJf3ax\"}]},{},[\"G9z0Bl\",3,10,4]);
;NREUM.info={beacon:\"bam.nr-data.net\",errorBeacon:\"bam.nr-data.net\",licenseKey:\"" config/new-relic-browser-key "\",applicationID:\"5086561\",sa:1,agent:\"js-agent.newrelic.com/nr-476.min.js\"}
")]))

(defn $user-area [user]
  [:li.user-area
   [:a {:href "/logout"}
    [:img.avatar {:src (:avatar-url user)}]
    " Log Out"]])

(defn $navbar [{:keys [user hide-search page-uri full-width? show-stars?]}]
  [:header.navbar
   [:div
    {:class (if full-width? "container-fluid" "container")}
    [:div.row
     [:div
      {:class (if full-width?
                "col-md-12"
                "col-md-10 col-md-offset-1")}
      [:a.navbar-brand {:href "/"}
       [:i.fa.fa-rocket]
       "ClojureDocs"]
      #_[:div.navbar-brand.clojure-version
         (:version search/clojure-lib)]
      [:button.btn.btn-default.navbar-btn.pull-right.mobile-menu
       [:i.fa.fa-bars]]
      [:ul.navbar-nav.nav.navbar-right.desktop-navbar-nav
       (when hide-search
         [:li
          [:div.navbar-brand.clojure-version
           [:a {:href (:gh-tag-url search/clojure-lib)}
            (:version search/clojure-lib)]]])
       [:li [:a {:href "/jobs"} "Jobs"]]
       [:li [:a {:href "/core-library"} "Core Library"]]
       [:li [:a {:href "/quickref"} "Quick Ref"]]
       (if user
         ($user-area user)
         [:li
          [:a {:href (gh-auth-url page-uri)}
           [:i.fa.fa-github-square] "Log In"]])
       (when show-stars?
         [:li
          [:iframe.gh-starred-count
           {:src "/github-btn.html?user=zk&repo=clojuredocs&type=watch&count=true"
            :allowtransparency "true"
            :frameborder "0"
            :scrolling "0"
            :width "100"
            :height "20"}]])]
      (when-not hide-search
        [:div.nav-search-widget.navbar-right.navbar-form
         [:form.search
          {:autocomplete "off"}
          [:input.placeholder.form-control
           {:type "text"
            :name "query"
            :placeholder "Looking for? (ctrl-s)"
            :autocomplete "off"}]]])]]
    (when-not hide-search
      [:div.row
       [:div.col-md-10.col-md-offset-1
        [:div.ac-results-widget]]])]])

(defn $mobile-navbar-nav [{:keys [user page-uri mobile-nav]}]
  [:div.mobile-nav-menu
   [:section
    [:h4 [:i.fa.fa-rocket] "ClojureDocs"]
    [:ul.navbar-nav.mobile-navbar-nav.nav
     [:li
      [:a {:href "/core-library"} "Core Library"
       [:span.clojure-version "(1.9.0 a14)"]]]
     [:li [:a {:href "/quickref"} "Quick Reference"]]
     (if user
       ($user-area user)
       [:li
        [:a {:href (gh-auth-url page-uri)}
         [:i.fa.fa-github-square] "Log In"]])]]
   (for [{:keys [title links]} mobile-nav]
     [:section
      [:h4 title]
      [:ul.navbar-nav.mobile-navbar-nav.nav
       (for [link links]
         [:li link])]])])

(defn md5-path [path]
  (try
    (-> path slurp util/md5)
    (catch java.io.FileNotFoundException e
      nil)))

(def clojuredocs-script
  [:script {:src (str "/cljs/clojuredocs.js?"
                      (md5-path "resources/public/cljs/clojuredocs.js"))}])

(def app-link
  [:link {:rel :stylesheet
          :href (str "/css/app.css?"
                     (md5-path "resources/public/css/app.css"))}])

(def bootstrap-link
  [:link {:rel :stylesheet
          :href (str "/css/bootstrap.min.css?"
                     (md5-path "resources/public/css/bootstrap.min.css"))}])

(def font-awesome-link
  [:link {:rel :stylesheet
          :href (str "/css/font-awesome.min.css?"
                     (md5-path "resources/public/css/font-awesome.min.css"))}])

(def opensearch-link
  [:link {:rel "search"
          :href "/opensearch.xml"
          :type "application/opensearchdescription+xml"
          :title "ClojureDocs"}])

(defn $main [{:keys [page-uri
                     content
                     title
                     body-class
                     user
                     page-data
                     meta
                     full-width?
                     show-survey-banner?] :as opts}]
  [:html5
   [:head
    [:meta {:name "viewport" :content "width=device-width, maximum-scale=1.0"}]
    [:meta {:name "apple-mobile-web-app-capable" :content "yes"}]
    [:meta {:name "apple-mobile-web-app-status-bar-style" :content "default"}]
    [:meta {:name "apple-mobile-web-app-title" :content "ClojureDocs"}]
    [:meta {:name "google-site-verification" :content "XjzqkjEPtcgtLjhnqAvtnVSeveEccs-O_unFGGlbk4g"}]
    (->> meta
         (map (fn [[k v]]
                [:meta {:name k :content v}])))
    [:title (or title "Community-Powered Clojure Documentation and Examples | ClojureDocs")]
    opensearch-link
    font-awesome-link
    bootstrap-link
    app-link
    [:script "// <![CDATA[\nwindow.PAGE_DATA=" (util/to-json (pr-str page-data)) ";\n//]]>"]]
   [:body
    (when body-class
      {:class body-class})
    ($mobile-navbar-nav opts)
    [:div.mobile-nav-bar
     ($navbar opts)]
    [:div.sticky-wrapper.mobile-push-wrapper
     (when config/staging-banner?
       [:div.staging-banner
        "This is the ClojureDocs staging site, where you'll find all the neat things we're working on."])
     [:div.desktop-nav-bar
      ($navbar opts)]
     [:div
      {:class (if full-width?
                "container-fluid"
                "container")}
      [:div.row
       [:div
        {:class (if full-width?
                  "col-md-12"
                  "col-md-10 col-md-offset-1")}
        content]]]
     [:div.sticky-push]]
    [:footer
     [:div.container
      [:div.row
       [:div.col-sm-12
        [:div.divider
         "- ❦ -"]]]
      [:div.row
       [:div.ctas
        [:div.col-sm-6.left
         "Brought to you by "
         [:a {:href "https://zacharykim.com"} "Zachary Kim"]
         ". "]
        [:div.col-sm-6.right
         [:iframe.gh-starred-count
          {:src "/github-btn.html?user=zk&repo=clojuredocs&type=watch&count=true"
           :allowtransparency "true"
           :frameborder "0"
           :scrolling "0"
           :width "80"
           :height "20"}]
         #_[:iframe {:src "/github-btn.html?user=zk&repo=clojuredocs&type=fork&count=true"
                     :allowtransparency "true"
                     :frameborder "0"
                     :scrolling "0"
                     :width "80"
                     :height "20"}]
         [:a.twitter-share-button {:href "https://twitter.com/share"
                                   :data-url "http://clojuredocs.org"
                                   :data-text "Community-powered docs and examples for #Clojure"
                                   :data-via "heyzk"}
          "Tweet"]]]
       [:script
        "!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');"]]]]
    (when (env/bool :cljs-dev)
      [:script {:src "/js/fastclick.min.js"}])
    (when (env/bool :cljs-dev)
      [:script {:src "/js/morpheus.min.js"}])
    (when (env/bool :cljs-dev)
      [:script {:src "/js/marked.min.js"}])
    clojuredocs-script
    ($ga-script-tag config/ga-tracking-id)
    ($new-relic-script-tag)
    ;; mobile safari home screen mode
    [:script
     "if((\"standalone\" in window.navigator) && window.navigator.standalone){
var noddy, remotes = false;

document.addEventListener('click', function(event) {

noddy = event.target;

while(noddy.nodeName !== \"A\" && noddy.nodeName !== \"HTML\") {
noddy = noddy.parentNode;
}

if('href' in noddy && noddy.href.indexOf('http') !== -1 && (noddy.href.indexOf(document.location.host) !== -1 || remotes))
{
event.preventDefault();
document.location.href = noddy.href;
}

},false);
}"]]])

(defn $avatar [{:keys [email login avatar-url] :as user}]
  [:a {:href (str "/u/" login)}
   [:img.avatar
    {:src (or avatar-url
              (str "https://www.gravatar.com/avatar/"
                   (util/md5 email)
                   "?r=PG&s=32&default=identicon"))}]])

(defn group-levels [path ns-lookup current-ns ls]
  (when-not (empty? ls)
    (->> ls
         (group-by first)
         (map (fn [[k vs]]
                (let [path (str path (when path ".") k)
                      vs (map #(drop 1 %) vs)
                      vs (remove empty? vs)]
                  {:part k
                   :path path
                   :ns (get ns-lookup path)
                   :current? (= current-ns path)
                   :cs (group-levels path ns-lookup current-ns vs)})))
         (sort-by :part))))

(defn group-namespaces [nss & [current-ns]]
  (->> nss
       (map #(str/split % #"\."))
       (group-levels nil (set nss) current-ns)))

(defn $ns-tree [{:keys [part path ns cs current?]}]
  [:li
   [:span {:class (when current? "current")}
    (if ns
      [:a {:href (str "/" ns)} part]
      part)]
   (when-not (empty? cs)
     [:ul (map $ns-tree cs)])])

(defn $namespaces [namespaces & [current-ns]]
  (let [ns-trees (group-namespaces namespaces current-ns)]
    [:ul.ns-tree
     (map $ns-tree ns-trees)]))

(defn $library-nav [{:keys [name namespaces]} & [current-ns]]
  (when-not (empty? namespaces)
    [:div.library-nav
     [:h5 "Namespaces"]
     ($namespaces (map :name namespaces) current-ns)]))

(defn ellipsis [s n]
  (cond
    (<= (count s) 3) s
    (> n (count s))  s
    :else (str (->> s
                    (take n)
                    (apply str))
               "...")))

(defn $recent [recent]
  (when-not (empty? recent)
    [:div.recent-pages
     [:h5 "Recent"]
     [:ul
      (for [{:keys [text href]} recent]
        [:li [:a {:href href} (ellipsis text 10)]])]]))

(defn four-oh-four [{:keys [user]}]
  ($main
    {:body-class "error-page"
     :hide-search true
     :user user
     :content
     [:div.row
      [:div.col-sm-8.col-sm-offset-2
       [:h1 "404"]
       [:a.four-oh-four {:href "http://emareaf.deviantart.com/art/Rich-Hickey-321501046"}
        [:img.four-oh-four {:src "http://fc04.deviantart.net/fs70/f/2012/229/a/6/rich_hickey_by_emareaf-d5bevsm.png"}]]]]}))

(defn five-hundred [{:keys [user]}]
  ($main
    {:body-class "error-page"
     :hide-search true
     :user user
     :content
     [:div.row
      [:div.col-sm-8.col-sm-offset-2
       [:h1 "500"]
       [:a.four-oh-four {:href "http://emareaf.deviantart.com/art/Rich-Hickey-321501046"}
        [:img.four-oh-four {:src "http://fc04.deviantart.net/fs70/f/2012/229/a/6/rich_hickey_by_emareaf-d5bevsm.png"}]]]]}))

(defn memo-markdown-file [path]
  (try
    (-> path
        slurp
        util/markdown)
    (catch java.io.FileNotFoundException e
      nil)))

(defn prep-for-syntaxhighligher [s]
  (when s
    (-> s
        (str/replace #"<pre><code>" "<pre>")
        (str/replace #"</code></pre>" "</pre>")
        (str/replace #"<pre>" "<pre class=\"clojure\">"))))

(when config/cache-markdown?
  (def memo-markdown-file (memoize memo-markdown-file)))
