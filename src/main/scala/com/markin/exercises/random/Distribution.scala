package com.markin.exercises.random

final case class Distribution[A](events: List[(A, Double)]) {

  def map[B](f: A => B): Distribution[B] =
    Distribution(events.map {
      case (a, p) => f(a) -> p
    })

  def flatMap[B](f: A => Distribution[B]): Distribution[B] =
    Distribution(events.flatMap {
      case (a, p1) => f(a).events.map {
        case (b, p2) => b -> (p1 * p2)
      }
    }).compact.normalize

  def compact: Distribution[A] = {
    val distinct = events.map {
      case (a, _) => a
    }.distinct

    def prob(a: A): Double =
      events.filter {
        case (x, _) => x == a
      }.map {
        case (_, p) => p
      }.sum

    Distribution(distinct.map {
      a => a -> prob(a)
    })
  }

  def normalize: Distribution[A] = {
    val totalWeight = events.map {
      case (_, p) => p
    }.sum

    Distribution(events.map {
      case (a, p) => a -> p / totalWeight
    })
  }

}

object Distribution {

  def uniform[A](events: List[A]): Distribution[A] = {
    val probability = 1.0d / events.length
    Distribution(events.map(event => event -> probability))
  }

  def discrete[A](events: List[(A, Double)]): Distribution[A] =
    Distribution(events).compact.normalize

}
