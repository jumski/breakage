# breakage

Turn your editor into a groovebox.

* lightning-fast and elegant programming of drum loops
* very jammable, expected to be run from your editor (tested with `vim-fireplace`)
* can drive SuperCollider or external MIDI gear

## TODO

- refactor data model to hash of arrays of steps, where each step is a map
- refactor `defpatch` to accept a transform-fn
- implement durations, maybe like this:

```clojure
:c3     + . . . 8______ + . . . + . .. .
```

## Usage

### Drumpatches

Drum patches are sequences of velocities.
Defined with `defdrumpatch` macro, which accept
a "map" of traks to steps (xoxox style).
Each step is 1/16 long.
If it is a number, this step will play. Number represents velocity (0 -> 12.7, 9 -> 127).
Any other non-number, non-keyword and non-list form is treated as empty step.

```clojure
(defdrumpatch :fast-hats
  :hat1 . . 3 . . . 3 .
  :hat2 . 2 . 2 . 2 . .
)
```

Tracks can be of various lengths. All of them will be `cycle`d to the length
of the longest one.

```clojure
(defdrumpatch :kick-snare
  :kick  1 . . .
  :snare . . 3 . . 2 3 .
)
```

Any lists will be evaled and results flattened into the steps, so you can do this:

```clojure
(defdrumpatch :snare-solo
   :snare1 (-> [1 . 5] cycle (take 4))
   ; same as:
   :snare1 [1 . 5 1]
   ; same as:
   :snare1 1 . 5 1
)
```

### Notemaps

Notemaps are for translating a note-label, like `:kick1` to a note.

```clojure
(defnotemap :amen-break-kit
  :kick1 :e2
  :kick2 :f2
  :snare1 :c#4
  :hat1 :f#3
)
```

### Patterns

Patches are used to play multiple patches simultaneously.
They are maps of track numbers to patch names.

```clojure
(defpattern :intro
  1  :kick-loop
  5  :fast-hats
  9  :atmo-pad
  10 :bass-lead
)
```

### Songs

Songs are sequences of patterns, that will be played in that order.

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
