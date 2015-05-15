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
  :kick1  9 . . 9 9 . . .
  ;; :kick1  9 . 3 9 . . 9 .

  ;; :snare1 . . . . . 9 . .
          ;; . . . . . 1 . .
  :snare3 . . . . 5
  ;;         9 . . 9 3 . 9 4
  ;; :snare1 . . 7
  ;; :snare1 . 2 . 3 . 7 . .
          ;; . 2 . 3 . 7 . .
          ;; . 2 . 3 . 7 . .
          ;; . . 5 . . 7 . .
  :chat1  2 . 4 . . 3
  :chat3  . . . . 4 . . .
  ;; :chat3  . 2 .
  ;; :chat1  . 4
  :kick6
          4 4 4 4 4 . . .
          . . . . . . . .
          . . . . . . . .
          . . . . . . . .
  ;; :chat1 3 2 1
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

;; (defn process-tick [metro ppqn player]
;;   (do
;;     (for [tick-len (* 1/4 (metro-tick metro)
          ;; [note velo] (notes-for-tick

; --- LIVE ---
(comment
  (defn restart-sequencing [pname bpm player]
    (do (stop-sequencing)
        (reset! sequencer (play-pattern! :intro 154 akai-player))))

  (defn stop-sequencing [pname]
    (do (stop @sequencer)
        (reset! current-step 0)))

    (stop @sequencer)
  (stop @sequencer)

  (restart-sequencing :intro 154 akai-player)
  (stop-sequencing :intro)


  ;; (let [hm {:a {:b {:c 1} :d 99}}]
  ;;   (update-in hm [:a :b :c] (comp inc inc inc)))

  ;; (future
  ;;   (doseq [trk [0 9]]
         ;; (Thread/sleep (beat-ms 1/4 150))))))
)

