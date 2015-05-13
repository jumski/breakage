(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [clojure.math.numeric-tower :only [floor]]))


;; (comment
;;   (do
    (def . nil)
    (def sink (midi-out "USB"))
    (def atat-pool (mk-pool))
    (def notemap {:kick1 60 :snare1 65 :chat1 66})
    (def patt (make-pattern [:kick1  9 . . 9 . . 9 .
                            :snare1 . . 4 . . 4 . .
                            :chat1  . 2 . 2 . 2 . 2]))

    (def sequencer (atom nil))

    (defn velo-for-step [steps step]
      (when-let [velo (-> steps cycle (nth step))]
        (-> velo inc (* 12.7))))

    (defn notes-for-step [steps step notemap]
      (let [names (keys steps)
            velos (map velo-for-step (vals steps))
            notes (map notemap names)]
        names
        ))
        ;; (zipmap notes velos)))

    (def current-step (atom 0))

    (defn play-current-step [patt notemap sink]
      (do
        (doseq [[name steps] patt
                :let [note (notemap name)
                      velo (velo-for-step steps @current-step)]
                :when (not (nil? velo))]
          (midi-note sink note velo 50))
        (swap! current-step inc)))

    (defn sequence [patt bpm notemap sink]
      "Starts sequencing midi messages to sink for pattern at given bpm.
      Uses notemap to translate track names to midi notes.
      Returns scheduled-fn, which can be stopped with stop."
      (let [step-ms (beat-ms 1/4 bpm)]
        (reset! sequencer (every step-ms play-current-step atat-pool))))

    (defn stop-sequence [] (stop @sequencer))
;;
;;
;;     )
;;
;;
;;
;;
;;
;; )
