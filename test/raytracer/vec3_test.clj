(ns raytracer.vec3-test
  (:require [clojure.test :refer :all])
  (:require [raytracer.vec3 :refer :all]))

(deftest vec3
  (testing "add")
  (is (= (add (create-vector 1.0 2.0 3.0) (create-vector 4.0 5.0 6.0)) [5.0 7.0 9.0]))

  (testing "subtract")
  (is (= (subtract [1.0 2.0 3.0] [4.0 5.0 6.0]) [-3.0 -3.0 -3.0]))

  (testing "scale")
  (is (= (scale [1.0 2.0 3.0] 2) [2.0 4.0 6.0]))

  (testing "dot")
  (is (= (dot [1.0 2.0 3.0] [4.0 5.0 6.0]) 32.0))

  (testing "magnitude")
  (is (= (magnitude [6.0 8.0]) 10.0))

  (testing "unit-vector")
  (is (= (unit-vector [1.0 0.0 0.0]) [1.0 0.0 0.0])))
