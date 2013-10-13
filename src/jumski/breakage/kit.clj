(ns jumski.breakage.kit
  (:import [java.io File])
  (:require [overtone.live :as live]))

(defn filename-to-samplename [filename]
  (let [parts (re-seq #"(.*)\.wav" filename)]
    (keyword (last (last parts)))))

(defn sample-to-samplename [sample]
  (let [filename (.name sample)]
    (filename-to-samplename filename)))

(defn sample-to-pair [sample]
  (let [samplename (sample-to-samplename sample)]
    [(keyword samplename) sample]))

(defn load-kit [path]
  (let [files (file-seq (File. (str path "/")))
        paths (map #(.getPath %) files)
        paths (filter #(re-find #"\.wav$" %) paths)
        samples (map live/sample paths)
        pairs (for [sample samples] (sample-to-pair sample))]
    (apply hash-map (flatten pairs))))
