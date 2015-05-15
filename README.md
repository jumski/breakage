# breakage

Turn your editor into a groovebox.

* designed for jamming, live hotswapping
* mainly for composing drums, but easily extendable for other purposes
* expected to be executed via VIM clojure repl (vim-fireplace) or other similar editor-repl
* sequences internal synth (SuperCollider) or external gear via MIDI
* export to Standard Midi File format
* powered by Clojure and Overtone

## Usage

### Patches

Patches are sequences of notes and velocities, that most of the time
will get looped.

You define them like this:

```clojure
(defpatch :intro
```


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
