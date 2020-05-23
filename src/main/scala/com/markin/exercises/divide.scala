package com.markin.exercises

object divide {
  def apply(dividend: Int, divider: Int): DivisionResult =
    if(divider == 0) Infinite else Finite(dividend / divider)

}

sealed trait DivisionResult
final case class Finite(quotient: Int) extends DivisionResult
case object Infinite extends DivisionResult


object MyAppDivide extends App {
  val q = divide(1000, 20) match {
    case Finite(quotient) => s"It's finite $quotient"
    case Infinite => "It's infinite"
  }
  println(q)

  assert(divide(100, 5) == Finite(20))
  assert(divide(90, 0) == Infinite)
}
