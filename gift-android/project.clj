(defproject gift/gift-android "0.1.0-SNAPSHOT"
  :description "Create gif files (version for android platform)"
  :url "https://github.com/split-brain/gift"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-parent "0.2.0"]]

  :dependencies [[org.clojure-android/clojure "1.6.0-RC1" :use-resources true]]

  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :java-source-paths ["src/java"] 

  :parent-project {:path "../project.clj"
                   :inherit  [:aot :source-paths :java-source-paths]}

  :android {:library true
            :target-version "14"})
