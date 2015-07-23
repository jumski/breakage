(ns jumski.breakage.akai-s2000
  (:use [overtone.music.pitch :only [note]])
  (:use [overtone.midi :only [midi-out]]))

; --- FUNCTIONS ---
(defn- velo-for-step [steps step]
  (when-let [velo (-> steps cycle (nth step))]
    (if (and (number? velo)
             (>= velo 0)
             (<= velo 9))
      (* (inc velo) 12.7))))

(def ^:private labels-to-notes
  "Maps track name to note"
  {:ab-chat1 :c#3    :do-chat  :c#4    :ses-chat1 :d#4
   :ab-chat2 :d#3                      :ses-chat2 :f#4
   :ab-chat3 :f#3
   :ab-chat4 :g#3
   :ab-chat5 :a#3

   :ab-kick1 :e2     :do-kick  :d3     :ses-kick1 :f#2      :dm-kick1 :a#2
   :ab-kick2 :f2                       :ses-kick2 :g#2
   :ab-kick3 :g2
   :ab-kick4 :a2
   :ab-kick5 :b2
   :ab-kick6 :c3

   :ab-snare1 :f3    :do-snare :g4    :ses-snare1 :a4
   :ab-snare2 :g3                     :ses-snare2 :b4
   :ab-snare3 :a3                     :ses-roll1  :c5
   :ab-snare4 :b3                     :ses-roll2  :d5
   :ab-snare5 :c4
   :ab-snare6 :d4
   :ab-snare7 :e4
   :ab-snare8 :f4
   })



(defn tname->note [tname]
  "Given track name, returns note number.
  Not number is trasnposed 1 octave up, this
  is because Overtone and Akai note numbers
  are shifted by 12 semitones (don't know why)."
  (let [n (or (tname labels-to-notes) tname)
        n (note n)
        n (+ 12 n)]
    n))
