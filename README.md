ParserSpec is a small internal scala-dsl to test parser combinators: 

    describe("exprparser") {
      factor mustParse "1" toAnything
    }

[![Build Status](https://travis-ci.org/flosell/parserspec.png?branch=master)](https://travis-ci.org/flosell/parserspec)


How to get it
=============

    resolvers+="Florians Maven Repo" at "http://dl.bintray.com/flosell/maven"
       
    libraryDependencies += "org.parserspec" % "parserspec_2.10" % "0.0.1-2013_06_23_1" % "test"
