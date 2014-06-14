(ns gift.core)

(defn- read-image
  "Read image form file-path to BufferedImage"
  [image-path]  
  (-> image-path
      (java.io.File.)
      (java.io.FileInputStream.)
      (javax.imageio.ImageIO/read)))

(defn make-gif
  "Produce gif from set of files.

Usage: (make-gif \"move.gif\" [\"person1.jpg\" \"person2.jpg\"])

"
  [output-path images]
  (let [encoder (-> (doto (gift.AnimatedGifEncoder. )
                      (.start output-path)
                      (.setDelay 200) ;; milliseconds
                      (.setRepeat 0)))]
    (doseq [i images]
      (.addFrame encoder (read-image i)))
    (.finish encoder)))
