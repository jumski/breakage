(ns jumski.breakage.tests.akai-s2000
  (:use [overtone.live :as o])
  (:use [overtone.midi :only [midi-out]])
  ;; (:use [overtone.midi :only [midi-out]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}]))

;; (def akai (first (midi-find-connected-devices "MIDI")))
;; (def akai (first (midi-find-connected-devices "MIDI")))

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

  (def akai (midi-out "USB"))

  ;; (o/midi-note-on midi-out-to-sampler 60 100)
  (midi-note akai 65 126 500 7)
  (midi-note-on akai 60 126)
  (midi-note-off akai 60)

  (future
    (doseq [note [60 60 60 65]]
      (do
        (midi-note akai note 126 400)
        (Thread/sleep 200))))
)
