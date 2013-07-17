Overview
========

ParserSpec is a small internal scala-dsl to test parser combinators:

    describe("exprparser") {
      factor mustParse "1" to "1"
      factor mustParse "1" to result matching (x => x == "1")
      factor mustParse "1" to anything
      // TODO: more tests
      
      factor must never parse "a"
    }


How to get it
=============

    resolvers+="Florians Maven Repo" at "http://dl.bintray.com/flosell/maven"
       
    libraryDependencies += "org.parserspec" % "parserspec_2.10" % "0.0.1-alpha7" % "test"


API changes from alpha-6 to alpha-7:
====================================

You need to specify the type holding the parsers. If your tests have the parser mixed in (like required in previous versions), add  `override val parsers = this`
The new Version is also able to handle parsers that are not mixed in. In this case, add something like `override val parsers = ArithParser` if your parsers are defined in ArithParser

    
Build
=====
[![Build Status](https://travis-ci.org/flosell/parserspec.png?branch=master)](https://travis-ci.org/flosell/parserspec)

