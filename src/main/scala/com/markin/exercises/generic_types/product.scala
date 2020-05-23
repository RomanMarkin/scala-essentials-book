package com.markin.exercises.generic_types

final case class Pair[A, B](one: A, two: B)

object MyAppProduct extends App {

  val pair = Pair[String, Int]("hi", 2)
  // pair: Pair[String,Int] = Pair(hi,2)

  println(pair.one)
  // res0: String = hi

  println(pair.two)
  // res1: Int = 2
}
