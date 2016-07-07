(ns jumski.breakage.state)

(def db
 "Stores patches, patterns and chains."
 (atom {}))

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
  "Makes hash-map of hitnames to steps from a flat list provided by defpatch"
  [flat]
  (let [raw-tracks  (split-on-keyword flat)
        avals       (map (comp rest flatten) raw-tracks)
        akeys       (map first raw-tracks)
        maxlen      (apply max (map count avals))
        cycled      (->> avals (map cycle))
        avals       (map #(take maxlen %) cycled)
        avals       (map vec avals)]
    (zipmap akeys avals)))

(defn- normalize-step
  "If item is a number or keyword, return it. Otherwise return nil"
  [item]
  (if (or (keyword? item) (number? item)) item))

(defn reset-state!
  "Resets patches atom." []
  (reset! db {}))

(defn- ensure-pattern-fn
  "If first item is a function, returns body as is.
   If first item is not a function, returns sequence
   of make-pattern prepended to body"
  [body]
  (let [[maybe-fn & body] body
        maybe-fn (if (and (ifn? maybe-fn)
                          (not (keyword? maybe-fn)))
                   maybe-fn
                   make-pattern)]
    (cons maybe-fn body)))

(defmacro defpatch
  "Parses steps into a pattern and stores in db atom"
  [pname & body]
  (let [[pattern-fn & body-tail] (ensure-pattern-fn body)
        body  (map #(if  (list? %)  (eval %) %) body-tail)
        body (flatten body)
        body (map normalize-step body)
        patt (make-pattern body)]
    `(do
       (swap! db assoc ~pname ~patt)
       true)))
