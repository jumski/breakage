(ns overtone-sandbox.experiment_2)
(use 'overtone.live)

; setup a sound for our metronome to use
(def kick (sample (freesound-path 2086)))

; setup a tempo for our metronome to use
(def one-twenty-bpm (metronome 194))

; this function will play our sound at whatever tempo we've set our metronome to
(defn looper [nome sound]
    (let [beat (nome)]
        (at (nome beat) (sound))
        (apply-at (nome (inc beat)) looper nome sound [])))

; turn on the metronome
(looper one-twenty-bpm kick)
