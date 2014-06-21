(defproject gift "0.1.0"
  :description "Create gif files"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]

  :profiles {:android-build {:dependencies [[com.google.android/android "4.0.1.2"]]
                             :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
                             :java-source-paths ["src/platform-specific/android/java/"]}

             :desktop-build {:java-source-paths ["src/platform-specific/desktop/java/"]}}

  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]

  :aot :all)
