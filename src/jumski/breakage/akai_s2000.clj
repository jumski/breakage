(ns jumski.breakage.akai-s2000
  (:use [overtone.live :only [midi-note note]])
  (:use [overtone.midi :only [midi-out]]))

; --- FUNCTIONS ---
(defn- velo-for-step [steps step]
  (when-let [velo (-> steps cycle (nth step))]
    (* (inc velo) 12.7)))

(def ^:private labels-to-notes
  "Maps track name to note"
  {:chat1 :c#3 :chat2 :d#3 :chat3 :f#3 :chat4 :g#3 :chat5 :a#3
   :kick1 :e2 :kick2 :f2 :kick3 :g2 :kick4 :a2 :kick5 :b2 :kick6 :c3
   :snare1 :f3 :snare2 :g3 :snare3 :a3 :snare4 :b3
   :snare5 :c4 :snare6 :d4 :snare7 :e4 :snare8 :f4})

(defn tname->note [tname]
  "Given track name, returns note number.
  Not number is trasnposed 1 octave up, this
  is because Overtone and Akai note numbers
  are shifted by 12 semitones (don't know why)."
  (let [n (or (tname labels-to-notes) tname)
        n (note n)
        n (+ 12 n)]
    n))
