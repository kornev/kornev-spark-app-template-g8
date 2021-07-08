package $package$.util.deploy

import scala.util.{ Failure, Success, Try }

import org.apache.log4j.LogManager

import $package$.util.logger.StrictLogging
import $package$.util.throwable._

abstract class SparkApp[A: ArgParser] extends StrictLogging {

  def program(args: A): Unit

  final def main(args: Array[String]): Unit =
    // format: off
    implicitly[ArgParser[A]].parse(args).fold(
      help  => Console.err.printf("%n%s", help),
      input =>
        Try(program(input)) match {
          case Failure(t) =>
            logger.error(t.getStackTraceAsString)
            LogManager.shutdown()
            throw t
          case Success(_) =>
            LogManager.shutdown()
        }
    )
    // format: on
}
