package $package$

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import com.typesafe.config.ConfigFactory

import $package$.layer.{ MainLogic, SparkEngine }
import $package$.main.$project;format="Camel"$Config

class $project;format="Camel"$Spec extends AnyFreeSpec with Matchers with $project;format="Camel"$SparkSuite { self =>

  "Фиктивный набор тестов" - {
    "всегда завершается удачно ничего не проверяя" in {

      sql("CREATE DATABASE report").collect()
      sql("USE report").collect()
      sql(
        """CREATE TABLE IF NOT EXISTS sales (
          |  `channel` STRING,
          |  `genre`   MAP<STRING, ARRAY<INT>>,
          |  `flight`  MAP<INT, ARRAY<INT>>
          |)
          |PARTITIONED BY (`dt` STRING)
          |STORED AS PARQUET
          |""".stripMargin
      ).collect()
      sql(
        """INSERT INTO TABLE sales PARTITION(dt='2020-10-08')
          |VALUES ('MATCH TV',map('adult',array(1601330359,1601330990,1601330348)),map(340405,array(1601330359,1601330990,1601330348))),
          |       ('TNT',map('adult',array(1600455849),'health',array(1600455849)),map(340405,array(1600455849))),
          |       ('ZVEZDA',map('adult',array(1601043382),'sport',array(1601043382)),map(340405,array(1601043382)))
          |""".stripMargin
      ).collect()

      import spark.sqlContext.implicits._

      (new MainLogic.live with SparkEngine { val spark = self.spark }).logic
        .run($project;format="Camel"$Config(ConfigFactory.empty()), "empty")

      spark
        .table("sales_affinity")
        .map(_.getSeq[Int](0))
        .collect()(0) should contain(340405)

      sql("DROP DATABASE report CASCADE").collect()
    }
  }
}
