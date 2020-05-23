package com.markin.exercises

case class Counter(count : Int = 0){
  def inc(increment : Int = 1): Counter = copy(count + increment)
  def inc: Counter = inc()
  def dec(decrement : Int = 1): Counter = copy(count - decrement)
  def dec: Counter = dec()
  def adjust(adder: Adder): Counter = copy(adder.add(count))
}

class Adder(amount: Int) {
  def add(in: Int): Int = amount + in
}

object MyAppCounter extends App {

  val cnt = Counter(10).inc().dec.inc.inc().count
  println(s"cnt = $cnt")

  println(s"Counter(): ${Counter().inc}")

  assert(Counter().inc.dec == Counter().dec.inc)
}