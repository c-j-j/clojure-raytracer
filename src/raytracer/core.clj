(ns raytracer.core
  (:require
    [raytracer.image :as im]
    [raytracer.vec3 :as vec3]
    [raytracer.ray :as ray]
    [raytracer.utils :as utils]))

(def nx 200)
(def ny 100)

; helper positions
(def lower-left-corner (vec3/create-vector -2 -1 -1))
(def horizontal (vec3/create-vector 4 0 0))
(def vertical (vec3/create-vector 0 2 0))
(def origin (vec3/create-vector 0 0 0))

; helper colours
(def white (vec3/create-vector 1 1 1))
(def blue (vec3/create-vector 0.5 0.7 1) )

(defn to-255 [n]
   (* 255.9 n))

(defn vector-towards-uv-point [u v]
  (let [mapped-u (vec3/scale horizontal u)
        mapped-v (vec3/scale vertical v)]
    (vec3/add (vec3/add lower-left-corner mapped-u) mapped-v)))

(defn map-to-0-to-1 [n]
  (* 0.5 (+ 1 n)))

(defn calculate-background-color [t]
  (vec3/add (vec3/scale white (- 1 t)) (vec3/scale blue t)))

(def sphere {:centre (vec3/create-vector 0 0 -1) :radius 0.5})

(defn hit-sphere?
  [sphere ray]
  (let [origin-to-sphere-centre (vec3/subtract (:origin ray) (:centre sphere))
        a (vec3/dot (:direction ray) (:direction ray))
        b (* 2 (vec3/dot origin-to-sphere-centre (:direction ray)))
        c (- (vec3/dot origin-to-sphere-centre origin-to-sphere-centre) (utils/square (:radius sphere)))
        discriminant (- (utils/square b) (* 4 a c))]
    (> discriminant 0)))

(defn colour-for-ray
  [ray]
  (let [ray-unit-vector (vec3/unit-vector (:direction ray))
        t (map-to-0-to-1 (vec3/y ray-unit-vector))]
    (if (hit-sphere? sphere ray) [1 0 0] (calculate-background-color t))))

(def buffer (for [j (range ny 0 -1)
                  i (range nx)]
              (let [u (/ i nx)
                    v (/ j ny)
                    ray-to-uv (ray/create-ray origin (vector-towards-uv-point u v))
                    colour (colour-for-ray ray-to-uv)]
                (map to-255 colour))))

(im/generate-image {:w nx :h ny :buffer buffer})