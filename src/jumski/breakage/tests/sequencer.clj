(ns jumski.breakage.tests.sequencer
  (:use [overtone.live :as o :only [beat-ms midi-note note]])
  (:use [overtone.midi :only [midi-out]])
  (:use [overtone.at-at :only [every mk-pool stop]])
  (:use [jumski.breakage.mindstorm :only [make-pattern patterns defpattern getpattern]])
  (:use [jumski.breakage.tests.akai-s2000 :as akai])
  (:use [clojure.pprint :only [pprint] :rename {pprint pp}])
  (:use [jumski.breakage.kit :only [load-kit]]))

; --- STATE ---
(def current-step (atom 0))
(def sequencer (atom nil))

; --- OPTS ---
(def atat-pool (mk-pool))

(def akai-player (akai/make-player "USB"))

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
  :chat1 3 2 1
  ;; :csnare . 3 . 3
  )

(defpattern :break1
  :kick1   9 . . . . 9 . .
  :kick2   . . 7 . 4 . . .
  :chat1   . 4 .
  :snare2  . . . . . . 4 .
  :snare3  . . . . . . . 4
  :snare1  . . 5 . . . . .
  ;; :snare2 . 2 . 2 4 5 2 .
  )

(defpattern :test :kick1 9)

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


(def amen-break (load-kit "samples/amen-break"))
(defn overtone-player [[tname steps] step]
  (let [sample (amen-break tname)
        vol    (nth steps step)
        vol    (* 10 vol)]
    (if-not (nil? vol)
      sample)))
      ;; (sample :vol vol))))
(def xxx (overtone-player [:snare1 [9 9 9 9]] 1))


; --- LIVE ---
(comment
  (do (stop @sequencer)
      (reset! current-step 0)
      (reset! sequencer (play-pattern! :intro 174 akai-player)))
  (stop @sequencer)






  (future
    (doseq [step (range 8)]
      (do
        (midi-note sink 60 126 50)
        ;; (play-and-advance :intro notemap sink)
        (Thread/sleep (beat-ms 1/4 150)))))
)

