(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [clojure.math.numeric-tower :only [floor]]))


(comment
  (do
    (def . nil)
    (def sink (midi-out "USB"))
    (def atat-pool (mk-pool))
    (def notemap {:kick1 60 :snare1 65 :chat1 66})
    (def patt (let [p (make-pattern [:kick1  9 . . 9 . . 9 .
                                     :snare1 . . 4 . . 4 . .
                                     :chat1  . 2 . 2 . 2 . 2])]
                {:steps p}))

    (def sequencer (atom nil))

    (defn step-to-velo [step]
      (let [velo-per-step (/ 127 10)]
        (int (* (dec step) velo-per-step))))

    (defn steps-to-play [{:keys [steps]} step notemap]
      (let [names (keys steps)
            velos (map step-to-velo (vals steps))
            notes (map notemap names)]
        (zipmap notes velos)))

    (defn play-step [patt bpm notemap sink]
      (doseq [[note velo] (steps-to-play patt notemap)
              :let [dura 50
                    velo (step-to-velo ]]
        (midi-note sink note velo dura)))

    (defn sequence [patt bpm notemap sink]
      "Starts sequencing midi messages to sink for pattern at given bpm.
      Uses notemap to translate track names to midi notes.
      Returns scheduled-fn, which can be stopped with stop."
      (let [step-ms (beat-ms 1/4 bpm)]
        (reset! sequencer (every step-ms play-step atat-pool))))

    (defn stop-sequence [] (stop @sequencer))


    )





;; (defn player
;;   "Plays all tracks from given pattern"
;;   [sink pname]
;;   (let [patt (pname @state/patterns)
;;         trks (tracks-to-play [patt cq])]
;;     (doseq [hit (keys pattern)]
;;       (play-track kit pattern hit beat))
;;     (apply-at (metro (inc beat)) #'player kit fnpattern (inc beat) []))))
;;
;; (defn play [kit pattern bpm]
;;   (do
;;     (stop)
;;     (metro :bpm bpm)
;;     (player kit pattern (metro))))

;; (def current-step (atom 0))
;; (defn- play-step [patt]
;;   (do
;;     (doseq [[tname velo] (tracks-to-play patt @current-step)
;;             :let [note (tname notemap)]]
;;       (midi-note akai





)
