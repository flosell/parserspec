name := "ParserSpec"

organization := "org.parserspec"

version := "0.0.1-2013_06_23_1"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" 

scalacOptions ++= Seq("-feature")

publishTo := Some("Bintray maven" at "https://api.bintray.com/maven/flosell/maven/parserspec")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

releaseSettings