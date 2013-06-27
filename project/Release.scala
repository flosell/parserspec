import sbt._
import Keys._
import sbtrelease._
import ReleasePlugin._
import ReleaseKeys._
import ReleaseStateTransformations._
import scala.util.matching.Regex
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import java.io.File
import sbt._
import Keys._
import annotation.tailrec
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbt.Package.ManifestAttributes
import scala.Some
import sbt.Value
import sbt.Inc
import sbt.Extracted
import ReleasePlugin.ReleaseKeys._
import Utilities._

object HelloBuild extends Build {
  private def vcs(st: State): Vcs = {
    // from ReleaseExtra.scala
    st.extract.get(versionControlSystem).getOrElse(sys.error("Aborting release. Working directory is not a repository of a recognized VCS."))
  }
  
  def replaceVersion(input: String, newVersion : String) : String = {
    val regex = new Regex("""(libraryDependencies\s+\+=\s+"org.parserspec"\s+%\s+"parserspec_[^"]*"\s+%\s+")([^"]+)(".*")""","head","version","tail")
    regex.replaceAllIn(input,m => m.group("head")+newVersion+m.group("tail") )
  }
  
  val updateVersionInReadme =  ReleaseStep(action = st => {
    val extracted = Project.extract(st)
    val vs = st.get(versions).getOrElse(sys.error("No versions are set! Was this release part executed before inquireVersions?"))
    val thisVersion = vs._1
    
	val input = Source.fromFile("README.md").mkString
    val replaced = replaceVersion(input,thisVersion)
    val writer = new PrintWriter(new File("README.md" ))
    writer.write(replaced)
    writer.close()
	vcs(st).add("README.md") !! st.log
    st
})
  lazy val root = Project(id = "ParserSpec",base = file(".")).settings(releaseSettings:_*).settings(
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,              
	  inquireVersions,                        
	  runTest,                                
	  setReleaseVersion,                      
	  updateVersionInReadme,
	  commitReleaseVersion,                   
	  tagRelease,                             
	  publishArtifacts,                       
	  setNextVersion,                         
	  commitNextVersion,                      
	  pushChanges                             
    )
  )
}