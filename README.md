ParserSpec is a small internal scala-dsl to test parser combinators: 

        describe("exprparser") {
                factor mustParse "1" toAnything
        }

[![Build Status](https://travis-ci.org/flosell/parserspec.png?branch=master)](https://travis-ci.org/flosell/parserspec)
