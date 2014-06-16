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
  :delay      - time in milliseconds between frames (default 200)
  :iterations - number of iterations (0 is for infinity)
  :quality    - quality of color quantization (should be > 0)
  :width      - width of resulting gif (if not provided the size of first frame is used)
  :height     - height of resulting gif (if not provided the size of first frame is used)

"
  [output-path images
   & {:keys [delay
             iterations
             quality
             width
             height]
      :or {delay 200
           iterations 0
           quality 10}}]
  (let [encoder (doto (gift.AnimatedGifEncoder.)
                  (.start output-path)
                  (.setRepeat iterations)
                  (.setQuality quality) ;; less is better
                  (.setDelay delay) ;; milliseconds
                  )]
    (when (and width height)
      (.setSize encoder width height))
    (doseq [i images]
      (.addFrame encoder (read-image i)))
    (.finish encoder)))
