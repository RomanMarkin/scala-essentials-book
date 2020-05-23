package com.markin.exercises.typeclass

/*trait Ordering[A] {
  def compare(a1: A, a2: A): Boolean
}


object Ordering {
  def fromLessThan[A](f: (A, A) => Boolean): Ordering[A] = new Ordering[A]{
    override def compare(a1: A, a2: A): Boolean = f(a1, a2)
  }
}*/


final case class Rational(nominator: Int, denominator: Int)

object Rational{
  implicit val minRationalOrdering = Ordering.fromLessThan[Rational]((r1, r2) => {
    r1.nominator.toDouble / r1.denominator.toDouble <
      r2.nominator.toDouble / r2.denominator.toDouble
  })
}

object OrderingUsage {
  def main(args: Array[String]): Unit = {
    val minOrdering = Ordering.fromLessThan[Int](_ < _)
    val maxOrdering = Ordering.fromLessThan[Int](_ > _)
    implicit val absOrdering = Ordering.fromLessThan[Int](math.abs(_) < math.abs(_))

    assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

    assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))

    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
      List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

  }
}