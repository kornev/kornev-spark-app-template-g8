package $package$.layer

import java.io.InputStreamReader
import java.nio.charset.Charset
import scala.language.implicitConversions

import org.apache.hadoop.fs.{ FileSystem, Path => HdfsPath }
import org.apache.spark.sql.SparkSession

trait HdfsStorage {
  val hdfs: HdfsStorage.Service
}

object HdfsStorage {

  trait Service {

    def inputReader(
        path: String,
        bufferSize: Int = 4096,
        charset: Charset = Charset.defaultCharset()
    ): InputStreamReader
  }

  def apply(spark: SparkSession): HdfsStorage.Service =
    new HdfsStorage.Service {
      private[this] implicit def wrapper(path: String): HdfsPath = new HdfsPath(path)
      private[this] val fileSystem                               = FileSystem.get(spark.sparkContext.hadoopConfiguration)

      def inputReader(path: String, bufferSize: Int, charset: Charset) =
        new InputStreamReader(fileSystem.open(path, bufferSize), charset)
    }
}
