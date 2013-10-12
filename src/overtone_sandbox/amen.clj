(ns overtone-sandbox.amen
  (:require [overtone-sandbox.kit :as kit]))

(def kit-amen (kit/load-kit "samples/amen-break"))

(defn hax-loop []
  (for [hit (vals kit-amen)] (do
                          (hit)
                          (Thread/sleep 85))))


;;;;;;;;;;;; stolen from overtone wiki
;; (defn looper [nome sound]
;;     (let [beat (nome)]
;;         (at (nome beat) (sound))
;;         (apply-at (nome (inc beat)) looper nome sound [])))
;;
;; (defn song [tempo]
;;   (let [kick-nome (metronome tempo)
;;         hat-nome (metronome (/ tempo 2))]
;;     (do
;;       (looper hat-nome (:chat1 kit))
;;       (looper kick-nome (:kick1 kit)))))

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

