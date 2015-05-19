(ns jumski.breakage.v2.sequencer
  (:require [overtone.live :as o]))

(def current-step
  "Stores current step for sequencer"
  (atom 0))

(defn start-sequencing
  "Starts sequencer, calling player-fn every each step.
  This interval is determined by bpm and step length."
  [player-fn bpm step-length]
  (for [step-ms ])

(def m (o/metronome 60))

