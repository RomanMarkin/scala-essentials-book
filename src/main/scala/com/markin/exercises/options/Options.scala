package com.markin.exercises.options

object Options {

  def addOptions(val1: Option[Int], val2: Option[Int]): Option[Int] =
    for {
      x <- val1
      y <- val2
    } yield x + y

  def addOptions2(val1: Option[Int], val2: Option[Int]): Option[Int] =
    val1.flatMap(x => val2.map(y => x + y))


  def addOptions(val1: Option[Int], val2: Option[Int], val3: Option[Int]): Option[Int] =
    for {
      x <- val1
      y <- val2
      z <- val3
    } yield x + y + z

  def addOptions2(val1: Option[Int], val2: Option[Int], val3: Option[Int]): Option[Int] =
    val1.flatMap { x =>
      val2.flatMap { y =>
        val3.map { z =>
          x + y + z
        }
      }
    }

  def divide(x: Int, y: Int): Option[Double] =
    if (y == 0)
      None
    else
      Some(x / y)


  def divide2(x: Int, y: Int): Option[Double] =
    try {
      Some(x / y)
    }
    catch {
      case e : ArithmeticException => None
    }


  def divideOptions(opt1: Option[Int], opt2: Option[Int]): Option[Double] =
    for {
      x <- opt1
      y <- opt2
      z <- divide(x, y)
    } yield z


  def calculator(operand1: String, operator: String, operand2: String): Unit = {
    val result: Option[Int] = for {
      x <- operand1.toIntOption
      y <- operand2.toIntOption
      res <- operator match {
        case "+" => Some(x + y)
        case "-" => Some(x - y)
        case "*" => Some(x * y)
        case "/" => Some(x / y)
        case _ => None
      }
    } yield res

    result match {
      case Some(r) => println(s"Result is $r")
      case None => println(s"There is an error calculating expression $operand1 $operator $operand2")
    }
  }

  def calculator2(operand1: String, operator: String, operand2: String): Unit = {
    def calcInternal(x: Int, y: Int): Option[Int] =
      operator match {
        case "+" => Some(x + y)
        case "-" => Some(x - y)
        case "*" => Some(x * y)
        case "/" => Some(x / y)
        case _ => None
      }

    val result: Option[Int] = for {
      x <- operand1.toIntOption
      y <- operand2.toIntOption
      res <- calcInternal(x, y)
    } yield res

    result match {
      case Some(r) => println(s"Result is $r")
      case None => println(s"There is an error calculating expression $operand1 $operator $operand2")
    }
  }

  def calculator3(operand1: String, operator: String, operand2: String): Unit = {
    def calcInternal(x: Int, y: Int): Option[Int] =
      operator match {
        case "+" => Some(x + y)
        case "-" => Some(x - y)
        case "*" => Some(x * y)
        case "/" => Some(x / y)
        case _ => None
      }

    val result: Option[Int] =
      operand1.toIntOption.flatMap {
        x =>
          operand2.toIntOption.flatMap {
            y =>
              calcInternal(x, y).map {
                res => res
              }
          }
      }

    result match {
      case Some(r) => println(s"Result is $r")
      case None => println(s"There is an error calculating expression $operand1 $operator $operand2")
    }
  }
}
