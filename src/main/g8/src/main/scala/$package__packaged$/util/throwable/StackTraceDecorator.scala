package $package$.util.throwable

import java.io.{ PrintWriter, StringWriter }

private[throwable] trait StackTraceDecorator {

  implicit class RichThrowable(val t: Throwable) {

    def getStackTraceAsString: String = {
      val sw = new StringWriter
      val pw = new PrintWriter(sw)

      t.printStackTrace(pw)

      sw.toString
    }
  }
}
