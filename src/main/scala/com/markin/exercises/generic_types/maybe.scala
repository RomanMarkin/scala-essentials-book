package com.markin.exercises.generic_types

sealed trait Maybe[A]{
  def fold[B](empty: B, full: A => B): B = this match {
    case Empty() => empty
    case Full(value) => full(value)
  }

  def map[B](f: A => B): Maybe[B] = this match {
    case Empty() => Empty[B]()
    case Full(value) => Full(f(value))
  }

  def flatMap[B](f: A => Maybe[B]):Maybe[B] = this match {
    case Empty() => Empty[B]()
    case Full(value) => f(value)
  }

  def map2[B](f: A => B): Maybe[B] =
    flatMap(v => Full(f(v)))
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

//val perhaps: Maybe[Int] = Empty[Int]
//val perhaps: Maybe[Int] = Full(1)
