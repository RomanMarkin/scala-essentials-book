package com.markin.exercises


trait Feline {
  def colour: String
  def sound: String
}

trait BigCat extends Feline {
  val sound = "roar"
}

case class Tiger(colour: String) extends BigCat
case class Lion(colour: String, maneSize: Int) extends BigCat
case class Panther(colour: String) extends BigCat

case class Cat(colour: String, food: String) extends Feline {
  val sound = "meow"
}


object ChipShop {
  def willServe(cat: Cat): Boolean = {
    if (cat.food == "Chips") true else false
  }

  def willServe2(cat: Cat): Boolean = cat match {
    case Cat(_, "Chips") => true
    case _ => false
  }
}

object MyApp1Cats extends App {

  val oswald = Cat("Black", "Milk")
  val henderson = Cat("Ginger", "Chips")
  val quentin = Cat("Tabby and white", "Curry")

  assert(!ChipShop.willServe(oswald))
  assert(ChipShop.willServe(henderson))
  assert(!ChipShop.willServe(quentin))

  assert(!ChipShop.willServe2(quentin))

}