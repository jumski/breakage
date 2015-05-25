(ns jumski.breakage.sequencer
  (:require [overtone.music.rhythm :refer [beat-ms]]
            [overtone.music.time :refer [apply-at now]]
            [overtone.at-at :refer [every mk-pool stop scheduled-jobs show-schedule]]))

(def current-step (atom 0))

(def playing? (atom false))

(def sequencer (atom nil))

(def atat-pool (mk-pool))

(defn play-and-advance
  "Plays current step via player-fn and advances current-step."
  [player-fn]
  (do
    (player-fn @current-step)
    (swap! current-step inc)))

(defn loop-pattern! [bpm player-fn]
  "Plays and advances. It recurs over time via apply-at."
  (let [step-ms (beat-ms 1/4 bpm)]
    (do
      (play-and-advance player-fn)
      (if @playing?
        (apply-at (+ (now) step-ms) loop-pattern! [bpm player-fn])))))

(defn stop-sequencing
  "Stops sequencer."
  []
  (do (reset! playing? false)
      (reset! current-step 0)))

(defn stop-every-sequencing
  "Stops sequencing started via every."
  []
  (do
    (stop sequencer)
    (reset! current-step 0)))


(defn start-every-sequencing
  "Starts sequencing playing each step with every."
  [bpm player-fn]
  (let [step-ms (beat-ms 1/4 bpm)]
    (do
      (stop-every-sequencing)
      (reset! sequencer (every step-ms #(play-and-advance player-fn) atat-pool)))))

(defn restart-sequencing
  "DEPRECATED: This shifts and goes out of phase after some more repeats.
  This is because apply-at is called after various number of notes being sent via midi.

  OLD DOC:
  Stops sequencer, resets current-step to 0 and starts sequencing
  at given bpm, playing each step with player-fn."
  [bpm player-fn]
  (do (stop-sequencing)
      (reset! playing? true)
      (reset! current-step 0)
      (loop-pattern! bpm player-fn)))
