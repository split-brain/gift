(defproject gift-desktop "0.1.0-SNAPSHOT"
  :description "Create gif files"
  :url "https://github.com/split-brain/gift"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-parent "0.2.0"]]

  :java-source-paths ["src/java/"] 

  :parent-project  {:path "../project.clj"
                    :inherit  [:dependencies :aot :source-paths :java-source-paths]})
