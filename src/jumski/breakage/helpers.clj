(ns jumski.breakage.helpers
  (:require [overtone.music.pitch :refer [note]]
            [overtone.studio.midi :refer [midi-note]]
            [jumski.breakage.state :refer [db]]
            [jumski.breakage.akai-s2000 :as akai]))

(defn notes-for-step [patch step]
  (for [[anote steps] patch
        :let [velo (nth (cycle steps) step)]
        :when (not (nil? velo))
        :let [velo (* 12.7 (inc velo))]]
    [anote velo]))

(defn player-fn [sink midimap step]
  (doseq [[midi-ch patch-name] midimap
          [anote velo] (notes-for-step (@db patch-name) step)
          :let [anote (akai/tname->note anote)
                anote (note anote)]]
    (midi-note sink anote velo 500 (dec midi-ch))))

