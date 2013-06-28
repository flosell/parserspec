package org.parserspec.example.parser

import scala.util.parsing.combinator._
  
trait Arith extends JavaTokenParsers {   
  def expr: Parser[String] = term~rep("+"~term | "-"~term) ^^ { case x => "expr"} 
  def term: Parser[String] = factor~rep("*"~factor | "/"~factor) ^^ { case x => "term" }
  def factor: Parser[String] = floatingPointNumber | "("~>expr<~")" ^^ {case e => e } 
  def setOfNumbers: Parser[Set[_>:Number]] = rep(floatingPointNumber) ^^ { case l => l.map(_.toDouble).toSet} 
}

