import JavaOptions._
import ScalaOptions._
import SparkOptions._
import Dependencies._

ThisBuild / organization := "$package$"
ThisBuild / version := {
  import scala.sys.process._
  import scala.util._

  Try(
    ("git describe --tags --abbrev=0" !!).stripPrefix("v").stripLineEnd
  ).getOrElse("dev")
}
ThisBuild / scalafixDependencies ++= rules

lazy val root = (project in file("."))
  .settings( // main
    name := "$project;format="norm"$",
    scalaVersion := "2.11.12",
    scalacOptions ++= scalaCompilerOptions,
    resolvers ++= sparkRepos,
    conflictManager := ConflictManager.latestRevision,
    libraryDependencies ++= spark ::: commons ::: tests,
    dependencyOverrides ++= overrides,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
  .settings( // jar
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
    assemblyJarName in assembly := {
      val jarName     = name.value
      val scalaBinary = scalaBinaryVersion.value
      val appVersion  = version.value

      s"\$jarName-\$scalaBinary-\${`SPARK-RELEASE`}-\$appVersion.jar"
    },
    assemblyExcludedJars in assembly := {
      val skipPackages = {
        val jarNames = Set(
          "unused-1.0.0.jar",
          "spark-tags_2.11-2.3.2.jar"
        )
        next: Attributed[File] => jarNames.contains(next.data.getName)
      }

      (fullClasspath in assembly).value.filter(skipPackages)
    },
    test in assembly := {}
  )
  .settings( // test
    fork in Test := true,
    javaOptions in Test ++= hotSpotOptions ::: derbyOptions,
    logBuffered in Test := false,
    parallelExecution in Test := false
  )
