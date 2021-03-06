(ns gift.core)

(defn- read-image
  "Read image from file-path to Image"
  [image-path]  
  (gift.Image/getImageFromFile image-path))

(defn- scale-image
  "Set size of [image] to be [w]idth x [h]eight"
  [image w h]
  (.scale image w h)
  image)

(defn make-gif
  "Produce gif from set of files.

Usage: (make-gif \"move.gif\" [\"person1.jpg\" \"person2.jpg\"])

Additional properties:\n
  :delay              - time in milliseconds between frames (default 200)
  :iterations         - number of iterations (0 is for infinity)
  :quality            - quality of color quantization (should be > 0)
  :width              - width of resulting gif (if not provided the size of first frame is used)
  :height             - height of resulting gif (if not provided the size of first frame is used)
  :transparent-color  - vector [r g b] which defines color that will be transparent in resulting gif. nil defines no transparent color (which is default)

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
           quality 10}}]
  (let [encoder (doto (gift.AnimatedGifEncoder.)
                  (.start output-path)
                  (.setRepeat (case iterations
                                1 -1 ;; -1 is for no repeat in AnimatedGifEncoder
                                0 0
                                (dec iterations)))
                  (.setQuality quality) ;; less is better
                  (.setDelay delay) ;; milliseconds
                  (.setTransparent (and transparent-color
                                        (apply #(gift.Color. %1 %2 %3) transparent-color))))]
    (doseq [i images]
      (.addFrame encoder
                 (let [image (read-image i)]
                   (if (and width height)
                     (scale-image image width height)
                     image))))
    (.finish encoder)))
