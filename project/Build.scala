import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "golfplay2"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
      "org.scalatest" %% "scalatest" % "1.9.1" % "test",
      "org.specs2" %% "specs2" % "1.12" % "test",
      "org.squeryl" %% "squeryl" % "0.9.5-2",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
      "com.jquery" % "jquery" % "1.7.1"

  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings( // Add your own project settings here      
      resolvers += "webjars" at "http://webjars.github.com/m2",
      testOptions in Test := Nil
  )

}
