(ns jumski.breakage.axiom
  (:use [overtone.live]))

(def fader (atom 0))
(def knobs (atom (repeat 8 0)))

(comment

  (event-debug-on)
  (event-debug-off)

  (on-event [:midi :control-change]
            (fn [e]
              (let [cc    (:note e)
                    value (:velocity e)]
                (your-instr note vel)))
            ::keyboard-handler)
  (remove-event-handler ::keyboard-handler)

)
