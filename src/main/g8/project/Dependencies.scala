import sbt._

object Dependencies {

  val spark = {
    import SparkOptions._

    List(
      "org.apache.spark" %% "spark-sql"            % `SPARK-RELEASE` % Provided,
      "org.apache.spark" %% "spark-hive"           % `SPARK-RELEASE` % Provided,
      "org.apache.spark" %% "spark-mllib"          % `SPARK-RELEASE` % Provided,
      "org.apache.spark" %% "spark-sql-kafka-0-10" % `SPARK-RELEASE`
    )
  }

  val commons = {
    val `BREEZE-NATIVES` = "0.13.2" // as in mllib
    val `DECLINE`        = "1.2.0"
    val `SCALA-CONFIG`   = "1.4.1"
    val `SCALA-LOGGING`  = "3.9.3"
    val `SLF4J`          = "1.7.31"

    List(
      "org.scalanlp"               %% "breeze-natives" % `BREEZE-NATIVES`,
      "com.monovore"               %% "decline"        % `DECLINE`,
      "com.typesafe"                % "config"         % `SCALA-CONFIG`,
      "com.typesafe.scala-logging" %% "scala-logging"  % `SCALA-LOGGING` exclude ("org.slf4j", "slf4j-api"),
      "org.slf4j"                   % "slf4j-api"      % `SLF4J`
    )
  }

  val overrides =
    Nil

  val rules = {
    val `ORGANIZE-IMPORTS` = "0.5.0"

    List(
      "com.github.liancheng" %% "organize-imports" % `ORGANIZE-IMPORTS`
    )
  }

  val tests = {
    val `LOG4J`     = "1.2.17"
    val `LZ4-JAVA`  = "1.8.0"
    val `SCALATEST` = "3.2.9"
    val `SLF4J`     = "1.7.31"

    // NOTE: https://issues.apache.org/jira/browse/SPARK-10057

    List(
      "log4j"          % "log4j"         % `LOG4J`     % Test,
      "org.lz4"        % "lz4-java"      % `LZ4-JAVA`  % Test,
      "org.scalatest" %% "scalatest"     % `SCALATEST` % Test,
      "org.slf4j"      % "slf4j-log4j12" % `SLF4J`     % Test
    )
  }
}
