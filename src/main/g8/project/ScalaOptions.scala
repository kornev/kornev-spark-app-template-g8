object ScalaOptions {

  // format: off
  val scalaCompilerOptions = List(
    "-encoding", "utf8",
    "-target:jvm-1.8",
    "-deprecation",
    "-feature",
    "-Xfatal-warnings",
    "-Xcheckinit",
    "-Xsource:2.12",
    "-language:existentials",
    "-language:higherKinds",
    "-language:postfixOps",
    "-Ywarn-unused-import",
    "-Ypartial-unification"
  )
  // format: on
}
