package $package$.main

import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.{ LocalDate, ZoneId }
import scala.util.Try

import cats.implicits._
import com.monovore.decline._

import $package$.util.deploy.ArgParser

final case class $project;format="Camel"$Args(configFile: String, processingDate: String)

object $project;format="Camel"$Args {

  implicit val parser: ArgParser[$project;format="Camel"$Args] = new ArgParser[$project;format="Camel"$Args] {

    def parse(args: Array[String]): Either[Help, $project;format="Camel"$Args] = {

      def configFile = Opts
        .option[String](
          long = "config",
          short = "c",
          metavar = "path to file",
          help = "Config file path on HDFS."
        )

      def processingDate = Opts
        .option[String](
          long = "date",
          short = "d",
          metavar = "date",
          help = "Processing date with format: (yyyy-MM-dd)."
        )
        .withDefault(LocalDate.now(ZoneId.of("Europe/Moscow")).minusDays(1).toString)
        .validate("Must be in ISO_LOCAL_DATE format")(text =>
          Try(LocalDate.parse(text, ISO_LOCAL_DATE)).isSuccess
        )

      Command(
        name = ".../spark-submit ... .jar",
        header = "Processing $project;format="Camel"$"
      )((configFile, processingDate) mapN { $project;format="Camel"$Args.apply }).parse(args)
    }
  }
}
