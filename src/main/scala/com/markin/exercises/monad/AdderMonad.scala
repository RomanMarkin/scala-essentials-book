package com.markin.exercises.monad

import scala.util.Try

object AdderMonad {

  val opt1 = Some(1)
  val opt2 = Some(2)
  val opt3 = Some(3)

  val seq1 = Seq(1)
  val seq2 = Seq(2)
  val seq3 = Seq(3)

  val try1 = Try(1)
  val try2 = Try(2)
  val try3 = Try(3)

  
  val res1: Option[Int] = for {
    val1 <- opt1
    val2 <- opt2
    val3 <- opt3
  } yield val1 + val2 + val3

  val res2: Seq[Int] = for {
    val1 <- seq1
    val2 <- seq2
    val3 <- seq3
  } yield val1 + val2 + val3

  val res3: Try[Int] = for {
    val1 <- try1
    val2 <- try2
    val3 <- try3
  } yield val1 + val2 + val3

}
