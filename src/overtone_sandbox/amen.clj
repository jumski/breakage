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

(defn filename-to-samplename [filename]
  (let [parts (re-seq #"(.*)\.wav" filename)]
    (keyword (last (last parts)))))

(defn sample-to-samplename [sample]
  (let [filename (.name sample)]
    (filename-to-samplename filename)))

(defn sample-to-pair [sample]
  (let [samplename (sample-to-samplename sample)]
    [(keyword samplename) sample]))

(def sample-map
  (apply hash-map (flatten (map sample-to-pair samples))))

(defn hit [samplename]
  ((samplename sample-map)))

(defn hitseq [samplenames])

;; :kick1 :kick2 :csnare :kick3 :kick4 :stab :revchat :chat4 :chat3 :rsnare :chat2 :chat1 :ssnare1 :ssnare2 :ssnare3 :ssnare4 :rroll :snare4 :snare3 :snare2 :skick :snare1 :rkick :ohat :crash
(def samplenames
  (keys sample-map))




;; (hax-loop)

;;
;; filtered (re-find #"\.wav$" (map .getPath samples))]
    ;; (map overtone.live/load-sample filtered)))


;; (defn samplename-to-samplekey [samplename] (keyword samplename))



;; (stereo-player (first sample-collection))
;; (filter
;;   #(re-find
;;      #"\.wav$"
;;      (map .getPath) samples)))
;; (stereo-player (last samples))

;; (filename-to-samplename "26899_VEXST_Semi_Snare_4.wav")
;; (println sample-collection)

