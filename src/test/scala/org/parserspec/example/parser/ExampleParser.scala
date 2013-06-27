package org.parserspec.example.parser

import scala.util.parsing.combinator._
  
trait Arith extends JavaTokenParsers {   
  def expr: Parser[Any] = term~rep("+"~term | "-"~term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}

