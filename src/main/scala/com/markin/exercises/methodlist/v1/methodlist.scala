package com.markin.exercises.methodlist.v1

import scala.annotation.tailrec

sealed trait IntList {
  def length: Int = length0(this)

  def product: Int = product0(this)

  def double: IntList = this match {
    case End => End
    case Pair(head, tail) => Pair(head * 2, tail.double)
  }

  @tailrec
  private def length0(intList: IntList, accumulator: Int = 0): Int = intList match {
    case End => accumulator
    case Pair(_, tail) => length0(tail, accumulator + 1)
  }

  @tailrec
  private def product0(intList: IntList, accumulator: Int = 1): Int = intList match {
    case End => accumulator
    case Pair(head, tail) => product0(tail, accumulator * head)
  }

}

case object End extends IntList

final case class Pair(head: Int, tail: IntList) extends IntList


object MyAppMethodListV1 extends App {
  val example = Pair(1, Pair(2, Pair(3, End)))

  assert(example.length == 3)
  assert(example.tail.length == 2)
  assert(End.length == 0)

  assert(example.product == 6)
  assert(example.tail.product == 6)
  assert(End.product == 1)

  assert(example.double == Pair(2, Pair(4, Pair(6, End))))
  assert(example.tail.double == Pair(4, Pair(6, End)))
  assert(example.tail.double == Pair(4, Pair(6, End)))
  assert(End.double == End)

  assert(Pair(10, End).double == Pair(20, End))
}