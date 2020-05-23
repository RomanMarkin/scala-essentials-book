package com.markin.exercises.enriched

object IntImplicits {

  implicit class IntOps(n: Int) {

    def yeah(): Unit =
      for {
        _ <- 0 until n
      } println("yeah")

    def times(f: Int => Unit): Unit =
      for {
        i <- 0 until n
      } f(i)

    def yeah2(): Unit =
      times(i => println(s"Yeah. It's a number $i"))
  }
}

object IntRunner {
  def main(args: Array[String]): Unit = {
    import IntImplicits.IntOps

    2.yeah()

    println("---")
    3.yeah2()

    println("---")
    (-1).yeah2()
  }
}
