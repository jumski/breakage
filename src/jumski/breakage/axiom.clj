(ns jumski.breakage.axiom
  (:use [overtone.live]))

(definst steel-drum [note 60 amp 0.8]
  (let [freq (midicps note)]
    (* amp
       (env-gen (perc 0.01 0.2) 1 1 0 1 :action FREE)
       (+ (sin-osc (/ freq 2))
          (rlpf (saw freq) (* 1.1 freq) 0.4)))))

(definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (saw freq)
     vol))

(definst spooky-house [freq 440 width 0.2
                         attack 0.3 sustain 4 release 0.3
                         vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (sin-osc (+ freq (* 20 (lf-pulse:kr 0.5 0 width))))
     vol))

(definst reece-bass [note 38 vol 0.9 gate 1]
  (let [attack 0.01
        sustain 0.8
        release 0.01
        freq (midicps note)
        amp (mouse-x 40 5000 EXP)
        detune (mouse-y 0 5 LIN)
        first-saw (- freq detune)
        second-saw (+ freq detune)]
    (let [amp (mouse-x 40 5000 EXP)
          detune (mouse-y 0 5 LIN)
          first-saw (- freq detune)
          second-saw (+ freq detune) ]
      (* (env-gen (adsr attack sustain release) gate 1 0 1 FREE)
         (lpf (saw first-saw) amp)
         vol))))
(def player (midi-poly-player reece-bass))
(event-debug-on)
(event-debug-off)
(stop)

event:  (:midi-device ALSA (http://www.alsa-project.org) A25 [hw:2,0,0] Axiom 25, USB MIDI, Axiom 25 0 :control-change)   ({:status nil, :note 116, :timestamp 683149972, :velocity 0, :data1 116, :msg #<FastShortMessage com.sun.media.sound.FastShortMessage@262c5402>, :device {:sinks 0, :info #<MidiInDeviceInfo A25 [hw:2,0,0]>, :name A25 [hw:2,0,0], :overtone.studio.midi/dev-num 0, :version 3.13.0-24-generic, :device #<MidiInDevice com.sun.media.sound.MidiInDevice@1642337a>, :sources 2147483647, :vendor ALSA (http://www.alsa-project.org), :transmitter #<MidiInTransmitter com.sun.media.sound.MidiInDevice$MidiInTransmitter@54419a00>, :description Axiom 25, USB MIDI, Axiom 25, :overtone.studio.midi/full-device-key [:midi-device ALSA (http://www.alsa-project.org) A25 [hw:2,0,0] Axiom 25, USB MIDI, Axiom 25 0]}, :channel 15, :command :control-change, :velocity-f 0.0, :data2-f 0.0, :data2 0, :dev-key [:midi-device ALSA (http://www.alsa-project.org) A25 [hw:2,0,0] Axiom 25, USB MIDI, Axiom 25 0]})




;; (definst reece-bass [freq 440 attack 0.01 sustain 0.8 release 0.1 vol 0.4]
;; )

(comment
  (def player (midi-poly-player steel-drum))
  (def player (midi-poly-player saw-wave))
  (def player (midi-poly-player spooky-house))

  (stop)
  (midi-player-stop)

  (demo 5 (let [amp (mouse-x 40 5000 EXP)
                detune (mouse-y 0 1 LIN)
                first-saw 80
                second-saw (+ first-saw detune) ]
            (+ (lpf (saw first-saw) amp)
               (lpf (saw second-saw) amp)
               (lpf (saw first-saw) (* 2 amp))
               (lpf (saw second-saw) (* 2 amp)))))
)
