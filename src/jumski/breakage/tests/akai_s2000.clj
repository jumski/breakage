(ns jumski.breakage.tests.akai-s2000
  (:use [overtone.live :as o :only [beat-ms midi-note]])
  (:use [overtone.midi :only [midi-out]])
  ;; (:use [overtone.midi :only [midi-out]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [jumski.breakage.mindstorm :as state])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [clojure.math.numeric-tower :only [floor]]))

;; (def akai (first (midi-find-connected-devices "MIDI")))
;; (def akai (first (midi-find-connected-devices "MIDI")))

(comment

  (defsynth testsaw [outno 0 freq 299]
    (out outno (sin-osc freq)))

  (def akai (midi-out "USB"))

  ;; (o/midi-note-on midi-out-to-sampler 60 100)
  (midi-note akai 65 126 500 0)
  (midi-note-on akai 60 126)
  (midi-note-off akai 60)

  (def testpat [60 60 65 60 60 67 60 60 69 60 60 71 60 60 60 71])

  (future
    (doseq [note (take 64 (cycle testpat))]
      (do
        (midi-note akai note 126 50)
        (Thread/sleep 150))))

  (state/defpattern :test

    :kick1        9 . . .   . . 9 .   9 . . 8   9 . 5 .

    ;; 65        . . 4 .   3 . . 3

    )

  (def bpm 154)
  (def pool (mk-pool))
  ;; (def sequencer (every (beat-ms 1/3 bpm) #(midi-note akai (-> (rand) (* 5) (+ 60) floor)  100 500 0) pool))
  (do
    (def seq-kicks (every (beat-ms 1 bpm) #(midi-note akai 60 100 500 0) pool))
    (Thread/sleep (beat-ms 1/3 bpm))
    (def seq-snares (every (beat-ms 1/3 bpm) #(midi-note akai (rand-nth snares) 100 500 0) pool)))
  (do
    (stop seq-kicks)
    (stop seq-snares))
  (def snares [65 66])
  ;; (def kicks [40 41 43 45 47])
  (defn note->akai [note]
    (midi-note akai note 100 500 0))

  (doseq [n (range 60 66)]
    (do
      (note->akai n)
      (Thread/sleep 200)))

  (defn rnd [] (-> (rand) (* 5) (+ 60) floor cycle))
  (defn smp [] (-> [60 65] (apply range) rand-nth cycle))



  (def akai (midi-out "USB"))
  (sequencer (:test @state/patterns) 154 akai-midimap akai)
  (stop sequencer)

  (defn sequence [patt bpm midimap sink]
    "Starts sequencing midi messages to sink for pattern at given bpm.
    Uses midimap to translate track names to midi notes.
    Returns scheduled-fn, which can be stopped with stop."
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
