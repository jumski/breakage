(ns jumski.breakage.tests.protocols)

(defprotocol Playable
  (play [step player] "Plays given step using a player")
  ; (play-step song step player)
  ; (play-step chain step player)
  ; (play-step pattern step player)
  )

(deftype Patch
  Playable
  (play-step [this step player]


(comment

  ;;;;; CHAIN - list of step-sequencables
  ;
  ; [intro intro dropdown break1 break2 breakdown outro]
  ;
  ;;;;; PATT - map of track numbers to patches
  ;
  ; track |  1 |  2 |  3 | ... | 16 |
  ; patch |  7 |  9 | 15 | ... | 15 |
  ;
  ;;;;; PATCH -


  (play song midi-player)
  (play song sc-player)

  (play chain midi-player)
  (play chain sc-player)

  (play pattern midi-player)
  (play pattern sc-player)

  (play track sc-player)
  (play track sc-player)


         )


