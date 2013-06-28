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
       
    libraryDependencies += "org.parserspec" % "parserspec_2.10" % "0.0.1-alpha4" % "test"
Build
=====
[![Build Status](https://travis-ci.org/flosell/parserspec.png?branch=master)](https://travis-ci.org/flosell/parserspec)

