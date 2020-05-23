package com.markin.exercises

import com.sun.net.httpserver.Authenticator

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator {
  def +(c: Calculation, operand: Int): Calculation = c match {
    case Success(result) => Success(result + operand)
    case Failure(reason) => Failure(reason)
  }

  def -(c: Calculation, operand: Int): Calculation = c match {
    case Success(result) => Success(result - operand)
    case Failure(reason) => Failure(reason)
  }

  def /(c: Calculation, operand: Int): Calculation = c match {
    case Success(result) => operand match {
      case 0 => Failure(s"Division by zero")
      case _ => Success(result / operand)
    }
    case Failure(reason) => Failure(reason)
  }
}

object MyAppCalculation extends App {
  assert(Calculator.+(Success(1), 1) == Success(2))
  assert(Calculator.-(Success(1), 1) == Success(0))
  assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))

  assert(Calculator./(Success(4), 2) == Success(2))
  assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
  assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))
}