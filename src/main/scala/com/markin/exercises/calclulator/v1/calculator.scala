package com.markin.exercises.calclulator.v1

sealed trait Expression {
  def eval: Calculation = this match {
    case Addition(left, right) => left.eval match {
      case Failure(reason) => Failure(reason)
      case Success(valueLeft) => right.eval match {
        case Failure(reason) => Failure(reason)
        case Success(valueRight) => Success(valueLeft + valueRight)
      }
    }
    case Subtraction(left, right) => left.eval match {
      case Failure(reason) => Failure(reason)
      case Success(valueLeft) => right.eval match {
        case Failure(reason) => Failure(reason)
        case Success(valueRight) => Success(valueLeft - valueRight)
      }
    }
    case Division(left, right)  => left.eval match {
      case Failure(reason) => Failure(reason)
      case Success(valueLeft) => right.eval match {
        case Failure(reason) => Failure(reason)
        case Success(valueRight) => valueRight match {
          case 0 => Failure("Division by zero")
          case _ => Success(valueLeft / valueRight)
        }
      }
    }
    case SquareRoot(expr) => expr.eval match {
      case Failure(reason) => Failure(reason)
      case Success(value) =>
        if (value < 0)
          Failure("Square root of negative number")
        else
          Success(Math.sqrt(value))
    }
    case Number(value) => Success(value)
  }
}

final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(expr: Expression) extends Expression
final case class Number(value: Double) extends Expression


sealed trait Calculation
final case class Success(value: Double) extends Calculation
final case class Failure(reason: String) extends Calculation

object MyAppCalculator extends App {
  assert(Addition(SquareRoot(Number(-1.0)), Number(2.0)).eval ==
    Failure("Square root of negative number"))
  assert(Addition(SquareRoot(Number(4.0)), Number(2.0)).eval == Success(4.0))
  assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))
}


