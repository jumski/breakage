(ns jumski.breakage.tests.step-sequences)

(defrecord Patch steps
  StepSequence
  (notes-for-step [step]
    (for [[tname steps] (:steps this)
          :let [velo (nth (cycle steps) step)]
          :when (not (nil? velo))
          :let [velo (* 12.7 (inc velo))]]
      [anote velo]))
  (notes-for-step [step]
    (notes-for-step step 1))
    ))

(defrecord Pattern tracks
  StepSequence
  (notes-for-step [step]
    (let [[tno trk] (:tracks this)]
      )
    ))

(defrecord Chain parts)
