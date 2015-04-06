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
