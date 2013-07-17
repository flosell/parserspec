package org.parserspec.example.parser.spec

import org.parserspec.ParserSpec
import org.parserspec.example.parser.Arith
import scala.language.postfixOps

class ExampleParserSpec extends ParserSpec with Arith{
	override val parsers = this
	describe("exprparser") {
	  parsers.factor mustParse "1" to "1"
	  parsers.factor mustParse "1" to result matching (x => x == "1")
	  parsers.factor mustParse "1" to anything
	  // TODO: more tests
	  
	  parsers.factor must never parse "a"
	  
	  parsers.setOfNumbers mustParse "1 2 3" to Set(1,2,3)
	}
}