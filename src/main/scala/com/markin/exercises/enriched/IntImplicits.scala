package com.markin.exercises.enriched

import scala.language.implicitConversions

object IntImplicits2 {

  class IntOps(n: Int) {

    def times(f: Int => Unit): Unit =
      for {
        i <- 0 until n
      } f(i)

    def yeah(): Unit =
      times(i => println(s"Yeah. It's a number $i"))
  }

  implicit def intToIntOps(n: Int): IntOps = new IntOps(n)
}


object IntRunner2 {
  def main(args: Array[String]): Unit = {
    import IntImplicits2.intToIntOps

    3.yeah()
  }
}
