package com.markin.exercises.trafficlight.v1

sealed trait TrafficLight {
  def next: TrafficLight
}

case object Green extends TrafficLight {
  def next: TrafficLight = Yellow(Green)
}

final case class Yellow(prevLight: TrafficLight) extends TrafficLight {
  def next: TrafficLight = prevLight match {
    case Green => Red
    case Red => Green
    case Yellow(_) => Red //throw RuntimeException
  }
}

case object Red extends TrafficLight {
  def next: TrafficLight = Yellow(Red)
}


object MyAppTrafficLightV1 extends App {
  assert(Green.next == Yellow(Green))
  assert(Green.next.next == Red)
  assert(Green.next.next.next.next == Green)
  assert(Yellow(Green).next == Red)
  assert(Yellow(Red).next == Green)
  assert(Yellow(Yellow(Green)).next == Red)
}

