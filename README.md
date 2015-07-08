Now, hold on a minute
=====================

This project was created to kill two birds with one stone.

At the time I wanted:

1. To understand how to build an sbt-web plugin.
1. To figure out if webjars in general were compelling enough to ditch the
   existing, and well established npm/bower ecosystem.

By bringing this project as far as I did, I was able to cross the first item
off my list, but unfortunately I also found the entire sbt-web setup to be
laborious and harmfully duplicate.

Wrapping packages, which are otherwise available via npm, in a 
maven-installable envelope disarms npm and can prevent it from effectively
managing the dependencies you need.

My advice is to steer clear of webjars, and sbt-web in general.
Write a `gulpfile.js` and a `package.json` and put them in a directory 
somewhere in your source tree. If you decide you absolutely want 
your front-end assets served by your Play app, feel free to have your 
gulpfile output your build results into your `public/` directory, but
for the most part, I feel like you should be serving all your assets 
from gulp during dev and copy the build results to your web root in prod.

If I still haven't convinced you of the dangers of this sort of tech, then 
please read on and enjoy the last of this tiny readme.


# sbt-babeljs [![Build Status](https://travis-ci.org/onelson/sbt-babeljs.svg?branch=master)](https://travis-ci.org/onelson/sbt-babeljs)

Provides an `sbt-web` source task for the [babeljs.io](http://babeljs.io)
transpiler.

Currently requires you to use node for your js engine (but I don't know why).
Add

    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

to your `build.sbt` to do this.

Change Log

v1.0.0.
============================

* Inital implementation.
* Built on babel-core v4.7.16.
