(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpattern]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [clojure.math.numeric-tower :only [floor]]))


;; (comment
;;   (do
    (def sink (midi-out "USB"))
    (def atat-pool (mk-pool))
    (def notemap {
      :chat1 :c#3 :chat2 :d#3 :chat3 :f#3 :chat4 :g#3 :chat5 :a#3

      :kick1 :e2 :kick2 :f2 :kick3 :g2 :kick4 :a2 :kick5 :b2 :kick6 :c3

      :snare1 :f3 :snare2 :g3 :snare3 :a3 :snare4 :b3
      :snare5 :c4 :snare6 :d4 :snare7 :e4 :snare8 :f4 })

    (defpattern :intro
      :kick1  9 . 3 9 . . 9 .
              9 . . 9 3 . 9 4
      :snare1 . . 7
      ;; :snare1 . 2 . 3 . 7 . .
      ;;         . 2 . 3 . 7 . .
      ;;         . 2 . 3 . 7 . .
      ;;         . . 5 . . 7 . .
      ;; :chat1  2 . 4 .
      :chat1 5 4 2
      ;; :csnare . 3 . 3
      )

    (defpattern :break1
      :kick1  9 . . . . 9 . .
      :chat1  1
      ;; :snare1 . . 5 . . . . .
      ;; :snare2 . 2 . 2 . . 2 .
      )

    (def sequencer (atom nil))

    (defn velo-for-step [steps step]
      (when-let [velo (-> steps cycle (nth step))]
        (-> velo inc (* 12.7))))

    (def current-step (atom 0))

    (defn play-and-advance [pname notemap sink]
      (do
        (doseq [[name steps] (pname @patterns)
                :let [noteno (-> name notemap o/note)
                      velo (velo-for-step steps @current-step)]
                :when (not (nil? velo))]
          (midi-note sink noteno velo 50))
        (swap! current-step inc)))

    (future
      (doseq [step (range 64)]
        (do
          (play-and-advance  notemap sink)
          (Thread/sleep (beat-ms 1/4 150)))))

    (defn play-pattern! [pname bpm notemap sink]
      "Starts sequencing midi messages to sink for pattern at given bpm.
      Uses notemap to translate track names to midi notes.
      Returns scheduled-fn, which can be stopped with stop."
      (let [step-ms (beat-ms 1/4 bpm)]
        (every step-ms #(play-and-advance pname notemap sink) atat-pool)))

    (let [pname :break1]
      (def sequencer
        (do (reset! current-step 0)
            (play-pattern! :intro 134 notemap sink))))

    (stop sequencer)
;;
;;
;;     )
;;
;;
;;
;;
;;
;; )
