(ns jumski.breakage.tests.protocols)

(defprotocol Steppable
  (has-notes? step resolution)
  (notes-for-step step resolution)
  )

