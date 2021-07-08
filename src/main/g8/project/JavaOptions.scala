object JavaOptions {

  // format: off
  val hotSpotOptions = List(
    "-Duser.timezone=MSK",
    "-Dfile.encoding=UTF-8",
    "-Djava.net.preferIPv4Stack=true",
    "-Dnetworkaddress.cache.ttl=0",
    "-XX:+PerfDisableSharedMem",
    "-XX:+DisableExplicitGC",
    "-XX:+StartAttachListener"
  )
  // format: on

  // format: off
  val derbyOptions = List(
    "-Dderby.stream.error.file=external/hive/derby.log"
  )
  // format: on
}
