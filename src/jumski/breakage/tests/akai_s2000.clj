(ns jumski.breakage.tests.akai-s2000
  (:use [overtone.live :as o]))

(def midi-out-to-sampler (first (midi-find-connected-devices "USB20MIDI")))
(def akai midi-out-to-sampler)

(comment

  (defsynth testsaw [outno 0 freq 299]
    (out outno (sin-osc freq)))

  (o/stop)
  (testsaw :outno 0)
  (testsaw :outno 1)
  (o/stop)
  (testsaw :outno 2)
  (testsaw :outno 2)
  (testsaw :outno 3)
  (testsaw :outno 4)
  (o/stop)
  (testsaw :outno 5)
  (testsaw :outno 6)
  (testsaw :outno 7)
  (o/stop)

  ;; (o/midi-note-on midi-out-to-sampler 60 100)
  (o/midi-note akai 60 100 1000 1)

  (doseq [n (range 126)]
    (o/midi-note-on midi-out-to-sampler n 100))

  )
