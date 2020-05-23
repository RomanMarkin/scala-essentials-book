package com.markin.exercises.methodlist.v3

sealed trait LinkedList[A] {
  def length: Int = this match {
    case End() => 0
    case Pair(_, tail) => tail.length + 1
  }

  def contains[A](item: A): Boolean = this match {
    case End() => false
    case Pair(head, tail) =>
      if (head.equals(item))
        true
      else
        tail.contains(item)
  }

  def apply(idx: Int): Result[A] = this match {
    case Pair(h, t) =>
      if (idx > 0)
        t.apply(idx - 1)
      else if (idx < 0)
        Failure("Negative list index given")
      else
        Success(h)
    case End() =>
      Failure("Index out of bounds")
  }

  def fold[B](end: B, f: (A, B) => B): B = this match {
    case End() => end
    case Pair(hd, tl) => f(hd, tl.fold(end, f))
  }

  def lengthV2: Int = fold(0, (_, acc:Int) => acc + 1)

  //def double: LinkedList[Int] = fold[LinkedList[Int]](End(), (head:Int, tail:LinkedList[Int]) => Pair(head * 2, tail))

/*  def product: A = this match {
    case End() => 1
    case Pair(head, tail) => head * tail.product
  }

  def double: LinkedList[A] = this match {
    case End() => End()[A]
    case Pair(head, tail) => Pair(head * 2, tail.double)
  }*/

  def map[B](f: A => B):LinkedList[B] = this match {
    case End() => End()
    case Pair(head, tail) => Pair(f(head), tail.map(f))
  }


  def foreach(f: A => Unit):Unit = this match {
    case End() => println("")
    case Pair(head, tail) =>
      f(head)
      tail.foreach(f)
  }
}

final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

sealed trait Result[A]
final case class Success[A](result: A) extends Result[A]
final case class Failure[A](reason: String) extends Result[A]


object MyAppMethodListV2 extends App {
  val example = Pair(1, Pair(2, Pair(3, End())))

  assert(example.length == 3)
  assert(example.tail.length == 2)
  assert(End().length == 0)

/*  assert(example.product == 6)
  assert(example.tail.product == 6)
  assert(End().product == 1)

  assert(example.double == Pair(2, Pair(4, Pair(6, End()))))
  assert(example.tail.double == Pair(4, Pair(6, End())))
  assert(example.tail.double == Pair(4, Pair(6, End())))
  assert(End().double == End())

  assert(Pair(10, End()).double == Pair(20, End()))*/

  val example2 = Pair(1, Pair(2, Pair(3, End())))
  assert(example2.contains(3))
  assert(!example2.contains(4))
  assert(!End().contains(0))
  // This should not compile
  // example.contains("not an Int")

  val example3 = Pair(1, Pair(2, Pair(3, End())))
  assert(example3(0) == Success(1))
  assert(example3(1) == Success(2))
  assert(example3(2) == Success(3))
  assert(example3(3) == Failure("Index out of bounds"))

  assert(example.lengthV2 == 3)
  assert(example.tail.lengthV2 == 2)
  assert(End().lengthV2 == 0)

  val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
  val listDoubled = list.map(_ * 2)
  val listPlusOne = list.map(_ + 1)
  val listDivThree = list.map(_ / 3)

  list.foreach(println(_))
  println("---")
  listDoubled.foreach(println(_))
  println("---")
  listDivThree.foreach(println(_))
  println("---")
}