package $package$.util.logger

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

trait StrictLogging {

  protected val logger: Logger =
    Logger(LoggerFactory.getLogger(getClass.getName.stripSuffix("\$")))
}
