package $package$.util.deploy

import com.monovore.decline.Help

trait ArgParser[A] extends (Array[String] => Either[Help, A]) {

  def parse(args: Array[String]): Either[Help, A]
  def apply(args: Array[String]): Either[Help, A] = parse(args)
}
