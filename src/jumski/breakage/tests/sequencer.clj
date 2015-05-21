(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop scheduled-jobs show-schedule]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpatch getpattern]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [jumski.breakage.kit :only [load-kit]]))

(def current-step (atom 0))
(def sequencer (atom nil))
(def playing? (atom false))

(def atat-pool (mk-pool))

(defn play-and-advance [player-fn]
  (do
    (player-fn @current-step)
    (swap! current-step inc)))

(defn loop-pattern! [bpm player-fn]
  "Starts sequencing midi messages. Plays each step using player-fn
  Returns scheduled-fn, which can be stopped with stop."
  (let [step-ms (beat-ms 1/4 bpm)]
    (do
      (play-and-advance player-fn)
      (if @playing?
        (o/apply-at (+ (o/now) step-ms) loop-pattern! [bpm player-fn])))))
      ;; (every step-ms #(play-and-advance player-fn) atat-pool))))

(defn stop-sequencing []
  (do (reset! playing? false)
      (reset! current-step 0)))

(defn restart-sequencing [bpm player-fn]
  (do (stop-sequencing)
      (reset! playing? true)
      (reset! current-step 0)
      (loop-pattern! bpm player-fn)))

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
