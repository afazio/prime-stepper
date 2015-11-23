# prime-stepper

### What does it do?

Why develop a program that lists prime numbers when you can instead write a
program that lists how many steps it takes to get to the next prime number?
This results in the same information but compressed down quite a bit.

For instance, instead of returning the first 20 primes:

    2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71

Why not instead return the steps it takes to get to the next prime, start at 1?

    1,1,2,2,4,2,4,2,4,2,4,2,6,4,2,4,6,6,2,6

Already, just in the first 20, you can see that the information is compressed.
With a larger example you would see a much larger compression.

This program returns the first x steps you request starting at 1.  So to get the
above first 20 steps, just run:

    prime-stepper 20

If you want to start at another number other than 1, add that as a second
argument:

    # Return next 20 steps, but starting at 1000 instead of 1.
    prime-stepper 20 1000

And that's it.

### Why?

I wrote this as a quick utility to spit out the first million steps starting at
100,000.  This data was then used in my HTML5 pixi-prime-viewer application.
Check out a demo [here](http://afaz.io/pixi-prime-viewer) and the source on
github [here](http://github.com/afazio/pixi-prime-viewer).

### Prerequisites

You'll need to install [leiningen](http://leiningen.org/) to run or build this
app.  Try running the `lein` command from your terminal to see if you already
have it installed.

### Usage

To get started without having to build a jar, simply run:

    lein run <steps> [<start]>

where `steps` is the number of steps to generate and `start` is what number to
start from.  `start` defaults to 1 if not provided.

If you want to have a jar that you can copy to other systems and run whenever,
build a jar:

    lein uberjar

This will spit out a jar file in the `target/uberjar/` directory.  Use this
standalone jar anywhere that java is installed:

    java -jar target/uberjar/prime-stepper-0.1.0-standalone.jar <steps> [<start>]

### Examples

Using leiningen:

    # Generate 5 steps starting at 1
    > lein run 5
    1,1,2,2,4

    # Same thing.  Explicitly set start at 1
    > lein run 5 1
    1,1,2,2,4

    # Generate 5 steps starting at 7
    > lein run 5 7
    4,2,4,2,4

Using jar:

    # Generate 5 steps starting at 1
    > java -jar prime-stepper-0.1.0-standalone.jar 5
    1,1,2,2,4

    # Same thing.  Explicitly set start at 1
    > java -jar prime-stepper-0.1.0-standalone.jar 5 1
    1,1,2,2,4

    # Generate 5 steps starting at 7
    > java -jar prime-stepper-0.1.0-standalone.jar 5 7
    4,2,4,2,4


## License

Copyright (c) 2015 Alfred J Fazio

Distributed under the MIT License
