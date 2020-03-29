(ns raytracer.image)

(do (spit "foo.ppm" "255\n")
    (spit "foo.ppm" "255\n" :append true))

(defn encode [buffer]
  (let [flattened (flatten buffer)]
    (clojure.string/join " " (mapv #(int (Math/floor %)) flattened))))

(defn generate-image
  [{:keys [w h buffer]}] (do
                           (println "creating image")
                           (spit "image.ppm" (str "P3" "\n" w " " h "\n" 255 "\n"))
                           (spit "image.ppm" (encode buffer) :append true)
                           (spit "image.ppm" "\n" :append true)
                           ))
