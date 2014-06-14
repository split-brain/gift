# gift

A minimal clojure library to convert set of images to gif file.
Backed by [AnimatedGifEncoder](http://www.java2s.com/Code/Java/2D-Graphics-GUI/AnimatedGifEncoder.htm)

## Usage

``` clojure
(:use [gift.core])
```

``` clojure
(make-gif "person.gif" ["file1.jpg" "file2.jpg" "file3.jpg"])
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
