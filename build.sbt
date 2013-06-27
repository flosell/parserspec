import sbtrelease._
import ReleaseStateTransformations._

name := "ParserSpec"

organization := "org.parserspec"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" 

scalacOptions ++= Seq("-feature")

publishTo := Some("Bintray maven" at "https://api.bintray.com/maven/flosell/maven/parserspec")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

