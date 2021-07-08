package $package$.main

import com.typesafe.config.{ Config => HoconConfig }

object $project;format="Camel"$Config {

  def apply(config: HoconConfig): $project;format="Camel"$Config =
    new $project;format="Camel"$Config(config)
}

class $project;format="Camel"$Config(config: HoconConfig) {}
