package com.markin.exercises.trafficlight.v2

sealed trait TrafficLight {
  def next: TrafficLight = this match {
    case Green => Yellow(Green)
    case Red => Yellow(Red)
    case Yellow(prevLight) => prevLight match {
      case Green => Red
      case Red => Green
      case Yellow(_) => Red // throw RuntimeException
    }
  }
}

case object Green extends TrafficLight
final case class Yellow(prevLight: TrafficLight) extends TrafficLight
case object Red extends TrafficLight


object MyAppTrafficLightV1 extends App {
  assert(Green.next == Yellow(Green))
  assert(Green.next.next == Red)
  assert(Green.next.next.next.next == Green)
  assert(Yellow(Green).next == Red)
  assert(Yellow(Red).next == Green)
  assert(Yellow(Yellow(Green)).next == Red)
}