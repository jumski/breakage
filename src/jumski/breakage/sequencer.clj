(ns jumski.breakage.sequencer
  (:require [overtone.music.rhythm :refer [beat-ms]]
            [overtone.music.time :refer [apply-at now]]
            [overtone.at-at :refer [every mk-pool stop scheduled-jobs show-schedule]]))

(def current-step (atom 0))

(def playing? (atom false))

(def sequencer (atom nil))

(def atat-pool (mk-pool :cpu-count 32))

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
  "Stops sequencing started via every."
  []
  (do
    (when-let [sqn @sequencer] (stop sqn))
    (stop @sequencer)
    (reset! sequencer nil)
    (reset! playing? false)
    (reset! current-step 0)))


(defn start-sequencing
  "Starts sequencing playing each step with every."
  [bpm player-fn]
  (let [step-ms (beat-ms 1/4 bpm)]
    (do
      (stop-sequencing)
      (reset! playing? true)
      (reset! sequencer (every step-ms
                               #(when @playing? (play-and-advance player-fn))
                               atat-pool)))))
