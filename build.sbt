import sbt.Keys._

val jgraph = "org.jgrapht" % "jgrapht-core" % "1.0.0"//"com.scitools.understand" % "plugin_1.1.3"
val scalactic = "org.scalactic" %% "scalactic" % "3.0.0"
val scalatest = "org.scalatest" %% "scalatest" % "3.0.0" % "test"

lazy val root = (project in file(".")).
    settings(
        name := "hw2",
        version := "1.0",
        scalaVersion := "2.11.8",

        // For ScalaTest, disables the buffered Output offered by sbt and uses its own method
        logBuffered in Test := false,

        libraryDependencies += jgraph,
        libraryDependencies += scalactic,
        libraryDependencies += scalatest

    )

