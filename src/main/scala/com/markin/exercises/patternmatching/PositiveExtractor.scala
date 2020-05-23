package com.markin.exercises.patternmatching

object Positive {
  def unapply(i: Int): Option[Int] =
    if (i > 0) Some(i)
    else None
}

object PositiveMatcherTester {
  def main(args: Array[String]): Unit = {
    assert("No" == (0 match {
      case Positive(_) => "Yes"
      case _           => "No"
    }))

    assert("Yes" == (42 match {
      case Positive(_) => "Yes"
      case _           => "No"
    }))
  }
}
