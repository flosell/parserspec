package org.parserspec.example.parser.spec

import org.parserspec.ParserSpec
import org.parserspec.example.parser.Arith
import scala.language.postfixOps

object ArithParser extends Arith {
  def giveMeFactorParser() = factor
  def apply() = factor
}

class ExampleParserSpecWithoutMixing extends ParserSpec{
  override val parsers = ArithParser
	describe("exprparser") {
	  ArithParser.factor mustParse "1" to "1"
	  ArithParser.giveMeFactorParser() mustParse "1" to result matching (x => x == "1")
	  ArithParser() mustParse "1" to anything
//	  // TODO: more tests
//	  
//	  factor must never parse "a"
//	  
//	  setOfNumbers mustParse "1 2 3" to Set(1,2,3)
	}
}