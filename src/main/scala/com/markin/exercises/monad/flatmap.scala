package com.markin.exercises.monad

sealed trait Maybe[A]{
  def flatMap(f: A => Maybe[A]): Maybe[A] = this match {
    case Empty() => Empty()
    case Full(value) => f(value)
  }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

object FlatMap {
  def mightFail1: Maybe[Int] =
    Full(1)

  def mightFail2: Maybe[Int] =
    Full(2)

  def mightFail3: Maybe[Int] =
    Empty()

  def main(args: Array[String]): Unit = {
    val r2 =
      mightFail1.flatMap { x =>
        mightFail2.flatMap { y =>
          mightFail3.flatMap { z =>
            Full(x + y + z)
          }
        }
      }

    println(r2)

  }
}
