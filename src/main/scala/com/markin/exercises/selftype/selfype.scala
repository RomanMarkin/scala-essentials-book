package com.markin.exercises.selftype

// Scala Program that uses self type

trait A {
  def x = 1
}

// trait extend another trait
trait B extends A {
  override def x = super.x * 5
}

// trait extend another trait
trait C1 extends B {
  override def x = 2
}

// trait extend another trait
trait C2 extends A {
  this: B =>
  override def x = 3
}

// Creating object
object SelfTypeRunner {
  // Main method
  def main(args: Array[String]) {
    println((new C1 with C2).x)
    println((new C2 with B).x)
  }
}
