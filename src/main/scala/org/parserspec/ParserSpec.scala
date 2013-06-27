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

  case class ParserWord[T](parser: Parser[T]) {
    def mustParse(s: String) = MustParsePart(parser, s)
  }

  case class MustParsePart[T](parser: Parser[T], s: String) {
    def to(expectedResult: T) = it("must parse '" + s + "' to " + expectedResult) {
      parsing(s)(parser) should equal(expectedResult)
    }
    
    def toAnything = it("must succeed parsing'"+s+"'") {parsing(s)(parser)}
  }

  implicit def toParserWord[T](parser: Parser[T]) = ParserWord(parser)
}