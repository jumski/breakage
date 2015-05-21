(ns jumski.breakage.sequencer
  (:require [overtone.music.rhythm :refer [beat-ms]]
            [overtone.music.time :refer [apply-at now]]))

(def current-step (atom 0))
(def playing? (atom false))

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

(defn restart-sequencing
  "Stops sequencer, resets current-step to 0 and starts sequencing
  at given bpm, playing each step with player-fn."
  [bpm player-fn]
  (do (stop-sequencing)
      (reset! playing? true)
      (reset! current-step 0)
      (loop-pattern! bpm player-fn)))
