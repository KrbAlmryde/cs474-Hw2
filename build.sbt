import sbt.Keys._

lazy val root = (project in file(".")).
settings(
    name := "hw2",
    version := "1.0",
    scalaVersion := "2.11.8"
)

