package com.markin.exercises.sequence

object LibMethods {

  def min(seq: Seq[Int]): Option[Int] = {
    seq.foldLeft[Option[Int]](seq.headOption) {
      (acc, elValue) =>
        acc.flatMap(accValue =>
          Some(math.min(accValue, elValue)))
    }
  }

  def min2(seq: Seq[Int]): Int =
    seq.foldLeft(Int.MaxValue)(math.min)

  def distinct(seq: Seq[Int]): Seq[Int] =
    seq.foldLeft(Seq.empty[Int]) {
      (currentSeq, element) =>
        if (currentSeq.contains(element))
          currentSeq
        else
          currentSeq :+ element
    }

  def reverse(seq: Seq[Int]): Seq[Int] =
    seq.foldLeft(Seq.empty[Int]){
      (currentSeq, element) =>
        element +: currentSeq
    }

  def map[A, B](seq: Seq[A], f: A => B): Seq[B] =
    seq.foldRight(Seq.empty[B]){
      (a, newSeq) =>
        f(a) +: newSeq
    }

  def foldLeft[A, B](seq: Seq[A])(z: B)(f: (B, A) => B): B = {
    var res: B = z
    seq.foreach(
      a =>
        res = f(res, a)
    )
    res
  }
}


object LibMethodsApp extends App {
  val min1 = LibMethods.min(Seq.empty)
  println(s"Min value of empty Seq $min1 calculated by min() method")
  val min2 = LibMethods.min2(Seq.empty)
  println(s"Min value of empty Seq $min2 calculated by min2() method")

  val multiset = Seq(1, 1, 2, 4, 3, 4)
  multiset.distinct.foreach(println)
}
