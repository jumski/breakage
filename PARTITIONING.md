  #_=> )
((:hax) ([1 2 3 4] [1 2 3 4]) (:omg) ([2 2 2 2] [3 4 4 4]))
user=> (->> aseq (partition-by keyword?) (partition 2) concat (map concat))
(((:hax) ([1 2 3 4] [1 2 3 4])) ((:omg) ([2 2 2 2] [3 4 4 4])))
user=> (->> aseq (partition-by keyword?) (partition 2) concat (map concat) first)
((:hax) ([1 2 3 4] [1 2 3 4]))
user=> (->> aseq (partition-by keyword?) (partition 2) concat)
(((:hax) ([1 2 3 4] [1 2 3 4])) ((:omg) ([2 2 2 2] [3 4 4 4])))
user=> (->> aseq (partition-by keyword?) (partition 2) concat first)
((:hax) ([1 2 3 4] [1 2 3 4]))
user=> (->> aseq (partition-by keyword?) (partition 2) concat first concat)
((:hax) ([1 2 3 4] [1 2 3 4]))
user=> (->> aseq (partition-by keyword?) (partition 2) concat first (apply concat))
(:hax [1 2 3 4] [1 2 3 4])
user=> (->> aseq (partition-by keyword?) (partition 2) concat (map #(apply concat)))

ArityException Wrong number of args (1) passed to: user$eval1164$fn  clojure.lang.AFn.throwArity (AFn.java:437)
user=> (->> aseq (partition-by keyword?) (partition 2) concat (map #(apply concat %)))
((:hax [1 2 3 4] [1 2 3 4]) (:omg [2 2 2 2] [3 4 4 4]))
user=> aseq
[:hax [1 2 3 4] [1 2 3 4] :omg [2 2 2 2] [3 4 4 4]]
user=> (def aseq2 [:hax [1 2 3 4] :omg [1 2 4 5] [1 2 4 5] :omg [1 2 4 5])

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:156)
user=> aseq

user=> (def aseq2 [:hax [1 2 3 4] :omg [1 2 4 5] [1 2 4 5] :omg [1 2 4 5]])
#'user/aseq2
user=> (->> aseq2 (partition-by keyword?) (partition 2) concat (map #(apply concat %)))
((:hax [1 2 3 4]) (:omg [1 2 4 5] [1 2 4 5]) (:omg [1 2 4 5]))
