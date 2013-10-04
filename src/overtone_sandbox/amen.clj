(ns overtone-sandbox.amen
  (:import (java.io File))
  (:use (overtone live)))

(def sample-paths
  (let [sample-files
        (file-seq (clojure.java.io/File. "samples/amen-break/"))]
    (filter
      #(re-find #"\.wav$" (.getPath %))
      sample-files)))

(def samples
  (map overtone.live/sample (sort sample-paths)))

(defn hax-loop []
  (for [sample samples] (do
                          (sample)
                          (Thread/sleep 85))))

(hax-loop)

;;
;; filtered (re-find #"\.wav$" (map .getPath samples))]
    ;; (map overtone.live/load-sample filtered)))


;; (defn filename-to-samplename [filename]
;;   (re-seq #"[a-z0-9]{5}_VEXST_([\w+_0-9]+)\.wav" filename)))

;; (defn samplename-to-samplekey [samplename] (keyword samplename))



;; (stereo-player (first sample-collection))
;; (filter
;;   #(re-find
;;      #"\.wav$"
;;      (map .getPath) samples)))
;; (stereo-player (last samples))

;; (filename-to-samplename "26899_VEXST_Semi_Snare_4.wav")
;; (println sample-collection)

