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

object HelloBuild extends Build {
  def replaceVersion(input: String, newVersion : String) : String = {
    val regex = new Regex("""(libraryDependencies\s+\+=\s+"org.parserspec"\s+%\s+"parserspec_[^"]*"\s+%\s+")([^"]+)(".*")""","head","version","tail")
    regex.replaceAllIn(input,m => m.group("head")+newVersion+m.group("tail") )
  }
  
  val updateVersionInReadme =  ReleaseStep(action = st => {
    val extracted = Project.extract(st)
    val version = extracted.get(Keys.version)
    
	val input = Source.fromFile("README.md").mkString
    val replaced = replaceVersion(input,version)
    val writer = new PrintWriter(new File("README.md" ))
    writer.write(replaced)
    writer.close()
	
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