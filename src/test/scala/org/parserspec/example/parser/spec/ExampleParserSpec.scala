package org.parserspec.example.parser.spec

import org.parserspec.ParserSpec
import org.parserspec.example.parser.Arith
import scala.language.postfixOps

class ExampleParserSpec extends ParserSpec with Arith{
	describe("exprparser") {
	  factor mustParse "1" toAnything
	  // TODO: more tests
	}
}