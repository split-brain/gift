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

Additional properties:\n
  :delay - time in milliseconds between frames (default 200)

"
  [output-path images
   & {:keys [delay]
      :or {delay 200}}]
  (let [encoder (-> (doto (gift.AnimatedGifEncoder. )
                      (.start output-path)
                      (.setRepeat 0)
                      (.setDelay delay) ;; milliseconds
                      ))]
    (doseq [i images]
      (.addFrame encoder (read-image i)))
    (.finish encoder)))

;; TODO iterations does not work?
;; TODO gif quality
