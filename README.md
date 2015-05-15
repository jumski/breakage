# breakage

Turn your editor into a groovebox.

* designed for jamming, live hotswapping of moving parts
* expected to be run from your editor
* sequences internal synth (SuperCollider) or external gear via MIDI
* mainly for composing drums, but easily extendable for other purposes
* powered by Clojure and Overtone

## Usage

### Drumpatches

Drum patches are sequences of velocities.
Defined with `defdrumpatch` macro, which accept
a "map" of traks to steps (xoxox style).
Steps are numbers from 0 to 9, so you can set velocities with a 12.7 precision.
Any non-keyword, non-number and no-sequence form will be treated as empty step.

```clojure
(defdrumpatch :fast-hats
  :hat1 . . 1 .
  :hat2 . . . 2
        . 3 . 4
  :hat3 . . 5 .
)
```

You can set various lengths of tracks. Shorten ones will get `cycle`'d to the
length of longest one.

```clojure
(defdrumpatch :kick-snare
  :kick  1 . . .
  :snare . . 3 .
         . 2 3 .
)
```

`defdrumpatch` is a powerful macro. Any sequences in steps
will get evaled and results flattened, so you can do this:

```clojure
(defdrumpatch :snare-solo
   :snare1 (-> [1 . 5] cycle (take 4))
   ; same as:
   :snare1 [1 . 5 1]
)
```

### Notemaps

Notemaps are needed to get a note from a drumpatch label.

```clojure
(defnotemap :amen-break-kit
  :kick1 :e2
  :kick2 :f2
  :snare1 :c#4
  :hat1 :f#3
)
```

### Patterns

Patterns are maps of track numbers to patchees.

```clojure
(defpattern :intro
  1  :kick-loop
  5  :fast-hats
  9  :atmo-pad
  10 :bass-lead
)
```

### Songs

Songs are sequences of patterns.

```clojure
(defsong :clojungle
  [:intro :intro :dropdown :break1 :break2 :breakdown :outro])
```

### Jamming

All of the patches, patterns and songs are loopable.
You need a player (see below)

```clojure
(def player (midi-sequencer "USB2MIDI"))

(loop-song :jungle player)
(loop-pattern :intro player)
(loop-patch :fast-hats player)

(stop player)
```



## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
