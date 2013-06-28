package org.parserspec
import org.scalatest.matchers.ShouldMatchers
import scala.util.parsing.input.CharSequenceReader
import org.scalatest.FunSpec
import scala.util.parsing.combinator.RegexParsers
import scala.language.implicitConversions
/**
 * Usage: 
 * <pre>
 * class SomeParserSpec extends AbstractParserTest with SomeParserClass{
 * 	
 * 	describe("the parser") {
 * 	  someParser mustParse "access" to ACCESS
 * 	  someParser mustParse "call" to CALL
 * 	}
 * }
</pre>
 */
abstract class ParserSpec extends FunSpec with ShouldMatchers with RegexParsers{
  private def parsing[T](s: String)(implicit p: Parser[T]): T = {
    // thanks to http://henkelmann.eu/2011/01/29/an_introduction_to_scala_parser_combinators-part_3_unit_tests
    //wrap the parser in the phrase parse to make sure all input is consumed
    val phraseParser = phrase(p)
    //we need to wrap the string in a reader so our parser can digest it
    val input = new CharSequenceReader(s)
    phraseParser(input) match {
      case Success(t, _) => t
      case NoSuccess(msg, _) => throw new IllegalArgumentException(
        "Could not parse '" + s + "': " + msg)
    }
  }
  
  case class never 
  
  case class SucceedWord[T](parser: Parser[T]) {
    def apply(s : String) = it("must not succeed parsing '"+s+"'") {
      try {
        parsing(s)(parser);
        fail("parsing succeeded but should have failed")
      } catch {case e: IllegalArgumentException => {/* nop, this is expected*/}}
    }
  }
  
  case class NeverWord[T](parser: Parser[T]) {
    def parse = SucceedWord(parser)
  }

  case class ParserWord[T](parser: Parser[T]) {
    def mustParse(s: String) = MustParsePart(parser, s)
    
    def must(n : never.type) = NeverWord(parser)
  }
  
  case class anything
  case class result
  case class resultWord[T](parser : Parser[T], s: String) {
    def matching(matcher :  T => Boolean)= it("must parse '" + s + "' to something matching to provided matcher") { 
      matcher(parsing(s)(parser)) should be (true) 
    }  
  }
  
  case class ToWord[T](parser : Parser[T], s: String) {
	def apply(a : anything.type) = it("must succeed parsing '"+s+"'") {parsing(s)(parser)}
	def apply(r : result.type) = resultWord(parser,s) 
	def apply(expectedResult : T) = it("must parse '" + s + "' to " + expectedResult) {
      parsing(s)(parser) should equal(expectedResult)
    }
  }
  
  case class MustParsePart[T](parser: Parser[T], s: String) {
	def to = ToWord(parser,s) 
  }

  implicit def toParserWord[T](parser: Parser[T]) = ParserWord(parser)
}