package $package$.layer

import scala.sys.{ addShutdownHook => shutdown }

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkEngine {

  val spark: SparkSession
}

object SparkEngine {

  def apply(settings: (String, String)*): SparkSession = {
    val properties = (new SparkConf)
      .set("spark.ui.enabled", "false")
      .setAll(settings)

    val spark = SparkSession.builder
      .config(properties)
      .enableHiveSupport
      .getOrCreate

    shutdown { spark.stop() }

    spark
  }
}
