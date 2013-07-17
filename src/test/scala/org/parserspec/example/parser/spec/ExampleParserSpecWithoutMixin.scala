package org.parserspec.example.parser.spec

import org.parserspec.ParserSpec
import org.parserspec.example.parser.Arith
import scala.language.postfixOps

object ArithParser extends Arith

class ExampleParserSpecWithoutMixing extends ParserSpec{
  val parsers = ArithParser
  import parsers._
	describe("exprparser") {
	  factor mustParse "1" to "1"
//	  factor mustParse "1" to result matching (x => x == "1")
//	  factor mustParse "1" to anything
//	  // TODO: more tests
//	  
//	  factor must never parse "a"
//	  
//	  setOfNumbers mustParse "1 2 3" to Set(1,2,3)
	}
}