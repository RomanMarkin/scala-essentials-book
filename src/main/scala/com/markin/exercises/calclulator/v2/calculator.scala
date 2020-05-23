package com.markin.exercises.calclulator.v2

import com.markin.exercises.generic_types.sum.v3.{Failure, Success, Sum}

sealed trait Expression {

  def eval: Sum[String, Double] = this match {
    case Addition(left, right) =>
      lift2(left, right, (l, r) => Success(l + r))
    case Subtraction(left, right) =>
      lift2(left, right, (l, r) => Success(l - r))
    case Division(left, right)  =>
      lift2(left, right, (l, r) =>
        if (r != 0)
          Success(l / r)
        else
          Failure("Division by zero"))
    case SquareRoot(expr) =>
      lift1(expr, v =>
        if (v < 0)
          Failure("Square root of negative number")
        else
          Success(Math.sqrt(v)))
    case Number(value) => Success(value)
  }

  private def lift1(e: Expression, f: Double => Sum[String, Double]) = {
    e.eval.flatMap(v => f(v))
  }

  private def lift2(l: Expression, r: Expression, f: (Double, Double) => Sum[String, Double]) = {
    l.eval.flatMap(left =>
      r.eval.flatMap(right =>
        f(left, right)))
  }

}

final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(expr: Expression) extends Expression
final case class Number(value: Double) extends Expression


object MyAppCalculatorV2 extends App {
  assert(Addition(SquareRoot(Number(-1.0)), Number(2.0)).eval ==
    Failure("Square root of negative number"))
  assert(Addition(SquareRoot(Number(4.0)), Number(2.0)).eval == Success(4.0))
  assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))

  assert(Addition(Number(1), Number(2)).eval == Success(3))
  assert(SquareRoot(Number(-1)).eval == Failure("Square root of negative number"))
  assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))
  assert(Division(Addition(Subtraction(Number(8), Number(6)), Number(2)), Number(2)).eval == Success(2.0))
}


