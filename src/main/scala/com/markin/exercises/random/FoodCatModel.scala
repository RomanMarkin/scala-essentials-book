package com.markin.exercises.random

sealed trait Food
case object CookedFood extends Food
case object RawFood extends Food

sealed trait Cat
case object HarassingCat extends Cat
case object SleepingCat extends Cat

object FoodCatModel {

  val foodDistribution: Distribution[Food] = Distribution.discrete(List(CookedFood -> 0.3, RawFood -> 0.7))

  def getCat(food: Food): Distribution[Cat] = food match {
    case CookedFood => Distribution.discrete(List(HarassingCat -> 0.8, SleepingCat -> 0.2))
    case RawFood => Distribution.discrete(List(HarassingCat -> 0.4, SleepingCat -> 0.6))
  }

  val foodCatDistribution: Distribution[(Food, Cat)] = for {
    food <- foodDistribution
    cat <- getCat(food)
  } yield (food, cat)

  val pHarassingCat: Double =
    foodCatDistribution.events.filter{
      case ((_, cat), _) => cat == HarassingCat
    }.map{
      case (_, probability) => probability
    }.sum

  val pCookedGivenHarassing: Option[Double] =
    foodCatDistribution.events.collectFirst {
      case ((CookedFood, HarassingCat), p) => p
    }.map(_ / pHarassingCat)


  def main(args: Array[String]): Unit = {
    foodCatDistribution.events.foreach {
      case ((food, cat), probability) => println(s"$food => $cat: $probability")
    }

    println(s"$HarassingCat outcome probability = $pHarassingCat")
    println(s"Ready Food given Harassing Cat => $pCookedGivenHarassing")
  }
}
