(ns jumski.breakage.mindstorm)

(def patterns (atom {}))

(defn- split-on-keyword
  "Splits input sequence on chunks, each starting with keyword"
  [pat]
  (->> pat
       (partition-by keyword?)
       (partition 2)
       concat
       (map #(apply concat %))
       (map vec)
       vec))

(defn make-pattern
  "Makes hash-map of hitnames to steps from a flat list provided by defpattern"
  [flat]
  (let [raw-tracks  (split-on-keyword flat)
        avals       (map (comp rest flatten) raw-tracks)
        akeys       (map first raw-tracks)
        maxlen      (apply max (map count avals))
        avals (map #(take maxlen %) (->> avals (map cycle)))]
    (zipmap akeys avals)))

(defn- normalize-step
  "If item is a number or keyword, return it.  Otherwise return nil"
  [item]
  (if (or (keyword? item) (number? item)) item))

(defmacro defpattern
  "Parses steps into a pattern and stores in patterns atom"
  [pname & body]
  (let [body (map normalize-step body)
        patt (make-pattern body)]
    (do
      (swap! patterns assoc pname patt)
      pname)))

(defmacro getpattern
  "Gets pattern by name"
  [pname]
  (@patterns pname))

(defn tracks-to-play [patt step]
  "Given pattern, returns map of track name to velocity,
  for tracks that should trigger a note on for this step."
  (->>
    (for [[n & [steps]] patt
          :let [velo (get steps step)]
          :when (not (nil? velo))]
      [n velo])
    flatten
    (apply hash-map)))

(comment
  (defpattern :intro

    :kick1    9 . . .   . . 9 .   9 . . 8   9 . 5 .

    :hat      . 2

    :snare    . . 4 .   3 . . 3

    :tomhi    . 1 1 2   . . 3 2

    :tomlo    3 . . .

  )
)
