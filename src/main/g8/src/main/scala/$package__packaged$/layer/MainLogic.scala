package $package$.layer

import org.apache.spark.sql.functions._

import $package$.main.$project;format="Camel"$Config

trait MainLogic {
  val logic: MainLogic.Service
}

object MainLogic {

  trait Service {
    def run(conf: $project;format="Camel"$Config, processingDate: String): Unit
  }

  import $package$.util.logger.StrictLogging

  trait live extends MainLogic {
    self: SparkEngine =>

    val logic = new MainLogic.Service with StrictLogging {

      def run(config: $project;format="Camel"$Config, processingDate: String): Unit = {
        import spark.sqlContext.implicits._

        spark
          .table("report.sales")
          .select(\$"channel" as "channel_id", explode(\$"genre") as List("break_type", "break_flight_start"))
          .createOrReplaceTempView("programme_genre")

        spark
          .table("report.sales")
          .select(\$"channel" as "channel_id", explode(\$"flight") as List("break_id", "break_flight_start"))
          .createOrReplaceTempView("programme_flight")

        spark
          .sql(
            """SELECT s.channel_id
              |     , s.break_type
              |     , c.break_id
              |  FROM programme_genre AS s INNER JOIN programme_flight AS c
              |    ON s.channel_id = c.channel_id
              |""".stripMargin
          )
          .agg(collect_set("break_id") as "all_break_ids")
          .createOrReplaceTempView("sales_affinity")
      }
    }
  }
}
