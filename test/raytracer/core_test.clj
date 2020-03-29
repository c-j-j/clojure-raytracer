(ns raytracer.core-test
  (:require [clojure.test :refer :all]
            [raytracer.core :refer :all]
            [raytracer.ray :as ray]
            [raytracer.vec3 :as vec3]
            ))

(deftest a-test
  (testing "calculates background colour as white when t=0"
    (is (= (calculate-background-color 0) [1.0 1.0 1.0]))))

(def test-ray-pointing-up (ray/create-ray (vec3/create-vector 0 0 0) (vec3/create-vector -2 -3 -1)))

(deftest b-test
  (testing "colour-for-ray"
    (is (= (colour-for-ray test-ray-pointing-up) [0.9504459314343183
                                                  0.97026755767913
                                                  1.0]))))
