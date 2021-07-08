package $package$.main

import com.typesafe.config.ConfigFactory

import $package$.layer.{ HdfsStorage, MainLogic, SparkEngine }
import $package$.util.deploy.SparkApp

object $project;format="Camel"$App extends SparkApp[$project;format="Camel"$Args] {
  val SPARK_APP_NAME = "$name$"

  def program(args: $project;format="Camel"$Args): Unit = {
    var config: $project;format="Camel"$Config = null
    val global = new MainLogic.live with HdfsStorage with SparkEngine {

      val spark = SparkEngine("spark.app.name" -> SPARK_APP_NAME)
      val hdfs  = HdfsStorage(spark)

      config = {
        val root = ConfigFactory
          .parseReader(hdfs.inputReader(args.configFile))
          .getConfig(SPARK_APP_NAME)

      $project;format="Camel"$Config(root)
      }
    }

    import global._

    logger.info(s"application `id` = \${spark.sparkContext.applicationId}")

    logic.run(config, args.processingDate)
  }
}
