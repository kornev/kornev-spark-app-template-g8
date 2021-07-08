package $package$

import org.apache.spark.sql.{ DataFrame, SparkSession }
import org.scalatest.{ BeforeAndAfterAll, Suite }
import $package$.layer.SparkEngine

trait $project;format="Camel"$SparkSuite extends BeforeAndAfterAll {
  self: Suite =>

  private[this] var _spark: SparkSession = _

  lazy val spark: SparkSession      = _spark
  lazy val sql: String => DataFrame = _spark.sql

  protected override def beforeAll: Unit = {
    super.beforeAll
    _spark = SparkEngine(
      "spark.app.name"          -> "spark-embedded",
      "spark.sql.warehouse.dir" -> s"\${sys.props("user.dir")}/external/spark/warehouse",
      "spark.master"            -> "local[2]"
    )
  }

  protected override def afterAll: Unit = {
    _spark.stop()
    super.afterAll
  }
}
