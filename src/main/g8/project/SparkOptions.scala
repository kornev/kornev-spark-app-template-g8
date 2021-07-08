import sbt._

object SparkOptions {

  val `SPARK-VENDOR`  = "Hortonworks"
  val `SPARK-RUNTIME` = "2.3.2.3.1.4.69-1"
  val `SPARK-RELEASE` = "2.3.2"

  val sparkRepos = List(
    "Hortonworks Releases" at "https://repo.hortonworks.com/content/repositories/releases/",
    "Jetty Releases" at "https://repo.hortonworks.com/content/repositories/jetty-hadoop/"
  )
}
