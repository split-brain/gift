(ns gift.core-test
  (:require [clojure.test :refer :all]
            [gift.core :refer :all]))

(def test-images ["resources/test_img1_640x480.png" "resources/test_img2_640x480.png" "resources/test_img3_640x480.png"])
(def out-dir "test/")

(deftest iterations
  (testing "Testing iterations"
    (let [iterations [1 2 5]
          result-file-name-prefix "iterations"]
      (doseq [iterations-count iterations]
        (make-gif (str out-dir result-file-name-prefix iterations-count ".gif")
                  test-images :iterations iterations-count)))))

(deftest quality
  (testing "Testing quality"
    (let [qualities [1 10 1000]
          result-file-name-prefix "quality"]
      (doseq [quality qualities]
        (make-gif (str out-dir result-file-name-prefix quality ".gif")
                  test-images :quality quality
                  :delay 2000)))))

(deftest scaling
  (testing "Testing scaling"
    (let [sizes [[320 240]
                 [160 120]
                 [80 60]] 
          result-file-name-prefix "scale"]
      (doseq [[width height] sizes]
        (make-gif (str out-dir result-file-name-prefix width "x" height ".gif")
                  test-images :width width :height height)))))


(deftest transparency
  (testing "Testing transparency"
    (let [black [0 0 0]
          white [255 255 255]
          blue  [0 0 255]]
      (make-gif (str out-dir "transparency-black" ".gif")
                test-images :transparent-color black
                :delay 500)
      (make-gif (str out-dir "transparency-white" ".gif")
                test-images :transparent-color white
                :delay 500)
      (make-gif (str out-dir "transparency-blue" ".gif")
                test-images :transparent-color blue
                :delay 500))))

(deftest frame-delay
  (testing "Testing frame delay"
    (let [delays [100 500 2000]
          result-file-name-prefix "delay"]
      (doseq [delay delays]
        (make-gif (str out-dir result-file-name-prefix delay ".gif")
                  test-images :delay delay)))))
