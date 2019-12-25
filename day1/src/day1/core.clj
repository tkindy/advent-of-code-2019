(ns day1.core
  (:gen-class))

(use '[clojure.core :only (comp)])

(defn calc-fuel-mass-help [mass]
  (- (Math/floor (/ mass 3)) 2))

(defn calc-fuel-mass [mass]
  (loop [cur-fuel (calc-fuel-mass-help mass), acc 0]
    (if (< cur-fuel 0)
        acc
        (recur (calc-fuel-mass-help cur-fuel) (+ acc cur-fuel)))))

(defn calc-fuel-masses [masses]
  (let [fuels (map (comp calc-fuel-mass clojure.edn/read-string) masses)]
    (reduce + 0 fuels)))

(defn -main [& args]
  (let [filename (first args)]
    (with-open [rdr (clojure.java.io/reader filename)]
      (calc-fuel-masses (line-seq rdr)))))
