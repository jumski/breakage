(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpattern getpattern]])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}]))

; --- STATE ---
(def current-step (atom 0))
(def sequencer (atom nil))

; --- OPTS ---
(def sink (midi-out "USB"))
(def atat-pool (mk-pool))
(def notemap {:chat1 :c#3 :chat2 :d#3 :chat3 :f#3 :chat4 :g#3 :chat5 :a#3
              :kick1 :e2 :kick2 :f2 :kick3 :g2 :kick4 :a2 :kick5 :b2 :kick6 :c3
              :snare1 :f3 :snare2 :g3 :snare3 :a3 :snare4 :b3
              :snare5 :c4 :snare6 :d4 :snare7 :e4 :snare8 :f4})

; --- PATTERNS ---
(defpattern :intro
  :kick1  9 . 3 9 . . 9 .
          9 . . 9 3 . 9 4
  ;; :snare1 . . 7
  :snare1 . 2 . 3 . 7 . .
          . 2 . 3 . 7 . .
          . 2 . 3 . 7 . .
          . . 5 . . 7 . .
  ;; :chat1  2 . 4 .
  :chat1 5 4 2
  ;; :csnare . 3 . 3
  )

(defpattern :break1
  :kick1  9 . . . . 9 . .
  :kick2  . . 7 . 4 . . .
  :chat1  . 6 .
  :snare2 . . . . . . 4 .
  :snare3 . . . . . . . 4
  :snare1 . . 5 . . . . .
  ;; :snare2 . 2 . 2 4 5 2 .
  )

(defpattern :test :kick1 9)

; --- FUNCTIONS ---
(defn velo-for-step [steps step]
  (when-let [velo (-> steps cycle (nth step))]
    (* (inc velo) 12.7)))


(defn akai-player [[tname steps] step]
  (let [noteno (-> tname notemap note (+ 12))
        velo   (velo-for-step steps step)]
    (if-not (nil? velo)
      (midi-note sink noteno velo 100))))

(defn play-and-advance [pname player]
  (do
    (doseq [trk (pname @patterns)]
      (player trk @current-step))
    (swap! current-step inc)))
(comment
  (play-and-advance :intro akai-player sink)
  )

(defn play-pattern! [pname bpm player]
  "Starts sequencing midi messages to sink for pattern at given bpm.
  Uses notemap to translate track names to midi notes.
  Returns scheduled-fn, which can be stopped with stop."
  (let [step-ms (beat-ms 1/4 bpm)]
    (every step-ms #(play-and-advance pname player) atat-pool)))



; --- LIVE ---
(comment
  (do (stop @sequencer)
      (reset! current-step 0)
      (reset! sequencer (play-pattern! :break1 154 akai-player)))
  (stop @sequencer)






  (future
    (doseq [step (range 8)]
      (do
        (midi-note sink 60 126 50)
        ;; (play-and-advance :intro notemap sink)
        (Thread/sleep (beat-ms 1/4 150)))))
)

