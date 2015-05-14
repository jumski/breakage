(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpattern]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [clojure.math.numeric-tower :only [floor]]))


;; (comment
;;   (do
    (def sink (midi-out "USB"))
    (def atat-pool (mk-pool))
    (def notemap {:kick1 60 :snare1 65 :chat1 66})
    (defpattern :intro
      :kick1  9 . 3 9 . . 9 .
              9 . . 9 3 . 9 4
      :snare1 . . . . . 7 . .
              . . . . . 7 . .
              . . . . . 7 . .
              . . 5 . . 7 . .
      ;; :chat1  2 . 4 .
      :chat1 5 4 2
      ;; :csnare . 3 . 3
      )

    (def sequencer (atom nil))

    (defn velo-for-step [steps step]
      (when-let [velo (-> steps cycle (nth step))]
        (-> velo inc (* 12.7))))

    (def current-step (atom 0))

    (defn play-and-advance [pname notemap sink]
      (do
        (doseq [[name steps] (pname @patterns)
                :let [note (notemap name)
                      velo (velo-for-step steps @current-step)]
                :when (not (nil? velo))]
          (midi-note sink note velo 50))
        (swap! current-step inc)))

    (future
      (doseq [step (range 64)]
        (do
          (play-and-advance  notemap sink)
          (Thread/sleep (beat-ms 1/4 150)))))

    (defn play-pattern! [pname bpm notemap sink]
      "Starts sequencing midi messages to sink for pattern at given bpm.
      Uses notemap to translate track names to midi notes.
      Returns scheduled-fn, which can be stopped with stop."
      (let [step-ms (beat-ms 1/4 bpm)]
        (every step-ms #(play-and-advance pname notemap sink) atat-pool)))

    (def sequencer (do (reset! current-step 0)
                       (play-pattern! :intro 154 notemap sink)))
    (stop sequencer)
;;
;;
;;     )
;;
;;
;;
;;
;;
;; )
