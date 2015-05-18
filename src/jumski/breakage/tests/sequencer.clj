(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpatch getpattern]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [jumski.breakage.kit :only [load-kit]]))

(def current-step (atom 0))
(def sequencer (atom nil))

(def atat-pool (mk-pool))
(def akai-player (akai/make-player "USB" 0))

(defn play-and-advance [pname player-fn]
  (do
    (doseq [trk (pname @patterns)]
      (player-fn trk @current-step))
    (swap! current-step inc)))
(comment
  (play-and-advance :intro akai-player sink)
  )

(defn play-pattern! [pname bpm player-fn]
  "Starts sequencing midi messages. Plays each step using player-fn
  which is c
  Uses notemap to translate track names to midi notes.
  Returns scheduled-fn, which can be stopped with stop."
  (let [step-ms (beat-ms 1/4 bpm)]
    (every step-ms #(play-and-advance pname player-fn) atat-pool)))

(defn stop-sequencing [pname]
  (do (stop @sequencer)
      (reset! current-step 0)))

(defn restart-sequencing [pname bpm player]
  (do (stop-sequencing pname)
      (reset! sequencer (play-pattern! pname bpm akai-player))))

; --- LIVE ---
(comment
  (restart-sequencing :break1 154 akai-player)
  (stop-sequencing :break1)

  (defpatch :break1
    :kick1   9 . . . . 9 . .
    :kick2   . . 7 . 4 . . .
    :chat2   . 4 .
    ;; :snare2  . . . . . . 4 .
    :snare2  . 2 . 2 . 4 4 4
    ;; :snare1  . . 5 . . . . .
    ;; :snare2 . 2 . 2 4 5 2 .
    )

  ;; (defn play-tracks [trks bpm]
  ;;   (doseq [[tname] trks
  ;;           :let [tname @tracks]
  ;;     (restart-sequencing tname 1


  (def amen-break (load-kit "samples/amen-break"))

  (defn sc-sample-player [[tname steps] step]
    (let [sample (amen-break tname)
          vol    (nth steps step)
          vol    (* 10 vol)]
      (if-not (nil? vol)
        sample)))

)
