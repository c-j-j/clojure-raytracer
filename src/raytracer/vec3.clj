(ns raytracer.vec3
  (:require
    [raytracer.utils :as utils]))

(defn create-vector [x y z]
  [(float x) (float y) (float z)])

(defn add [vec other]
  (mapv + vec other))

(defn subtract [vec other]
  (mapv - vec other))

(defn scale [vec n]
  (mapv #(* n %) vec))

(defn dot [a b]
  (reduce + (mapv * a b)))

(defn magnitude [v]
  (Math/sqrt (reduce + (map utils/square v))))

(defn unit-vector [v]
  (let [mag (magnitude v)]
    (mapv #(/ % mag) v)))

(defn x [v]
  (get v 0))
(defn y [v]
  (get v 1))
(defn z [v]
  (get v 2))

(y (create-vector 1 2 3))
