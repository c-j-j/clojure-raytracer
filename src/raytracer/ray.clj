(ns raytracer.ray
  (:require [raytracer.vec3 :as vec3]))

(defn create-ray
  [a b]
  {:origin a :direction b})

(defn point-at-position [ray t]
  (vec3/add (:origin ray) (vec3/scale (:direction ray) t)))
