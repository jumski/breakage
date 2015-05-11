(ns jumski.breakage.tests.akai-s2000
  (:use [overtone.live :as o])
  (:use [overtone.midi :only [midi-out]])
  ;; (:use [overtone.midi :only [midi-out]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [jumski.breakage.mindstorm :as state]))

;; (def akai (first (midi-find-connected-devices "MIDI")))
;; (def akai (first (midi-find-connected-devices "MIDI")))

(comment

  (defsynth testsaw [outno 0 freq 299]
    (out outno (sin-osc freq)))

  (def akai (midi-out "USB"))

  ;; (o/midi-note-on midi-out-to-sampler 60 100)
  (midi-note akai 65 126 500 7)
  (midi-note-on akai 60 126)
  (midi-note-off akai 60)

  (future
    (doseq [note (take 32 (cycle [60 60 60 65]))]
      (do
        (midi-note akai note 126 50)
        (Thread/sleep 150))))

  (state/defpattern :test

    :60        9 . . .   . . 9 .   9 . . 8   9 . 5 .

    ;; 65        . . 4 .   3 . . 3

    )

  ;; (notes-for-measure patt 17 1/4)
  (defn notes-for-measure
    [patt beat step]
    "Returns notes that are playing for given measure"
    )

  (defn foo
    [t val]
    (println val)
    (let [next-t (+ t 200)]
      (apply-at next-t #'foo [next-t (inc val)])))

  (defn midi-play-pattern
    [patt meas sink channel]
    "Plays pattern via midi channel"
    (for [notes (notes-for-measure patt meas)]
      (midi-note-on sink note 126 channel)))


  ;; (defn midi-player
  ;;   "Plays all tracks from given pattern"
  ;;   [sink metr patt]
  ;;   (for [[note velo]
  ;;         [[60 120] [60 120] [60 120] [ 65 120 ]]]
  ;;     (do
  ;;       (midi-note sink note velo 200)
  ;;       (apply-at (+ (now) 200) #'midi-player sink metr patt (+ (now) 200) [])
  ;;       )))







  )
