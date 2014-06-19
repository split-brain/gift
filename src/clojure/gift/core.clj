(ns gift.core)

(defn- read-image
  "Read image form file-path to BufferedImage"
  [image-path]  
  (-> image-path
      (java.io.File.)
      (java.io.FileInputStream.)
      (javax.imageio.ImageIO/read)))

(defn- scale-image
  "Set size of [image] to be [w]idth x [h]eight"
  [image w h]
  (let [resized (java.awt.image.BufferedImage. w h (.getType image))]
    (doto (.createGraphics resized)
      (.setRenderingHint java.awt.RenderingHints/KEY_INTERPOLATION
                         java.awt.RenderingHints/VALUE_INTERPOLATION_BILINEAR)
      (.drawImage image 0 0 w h 0 0 (.getWidth image) (.getHeight image) nil)
      (.dispose))
    resized))

(defn make-gif
  "Produce gif from set of files.

Usage: (make-gif \"move.gif\" [\"person1.jpg\" \"person2.jpg\"])

Additional properties:\n
  :delay              - time in milliseconds between frames (default 200)
  :iterations         - number of iterations (0 is for infinity)
  :quality            - quality of color quantization (should be > 0)
  :width              - width of resulting gif (if not provided the size of first frame is used)
  :height             - height of resulting gif (if not provided the size of first frame is used)
  :transparent-color  - vector [r g b a] which defines color that will be transparent in resulting gif. nil defines no transparent color (which is default)

"
  [output-path images
   & {:keys [delay
             iterations
             quality
             width
             height
             transparent-color]
      :or {delay 200
           iterations 0
           quality 10
           transparent-color nil}}]
  (let [encoder (doto (gift.AnimatedGifEncoder.)
                  (.start output-path)
                  (.setRepeat iterations)
                  (.setQuality quality) ;; less is better
                  (.setDelay delay) ;; milliseconds
                  (.setTransparent (if (nil? transparent-color)
                                     transparent-color
                                     (apply #(java.awt.Color. %1 %2 %3 %4) transparent-color))))]
    (doseq [i images]
      (.addFrame encoder
                 (let [image (read-image i)]
                   (if (and width height)
                     (scale-image image width height)
                     image))))
    (.finish encoder)))
