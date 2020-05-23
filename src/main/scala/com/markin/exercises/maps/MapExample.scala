package com.markin.exercises.maps

object MapExample {
  val people = Set(
    "Alice",
    "Bob",
    "Charlie",
    "Derek",
    "Edith",
    "Fred")

  val ages = Map(
    "Alice"   -> 20,
    "Bob"     -> 30,
    "Charlie" -> 50,
    "Derek"   -> 40,
    "Edith"   -> 10,
    "Fred"    -> 60)

  val favoriteColors = Map(
    "Bob"     -> "green",
    "Derek"   -> "magenta",
    "Fred"    -> "yellow")

  val favoriteLolcats = Map(
    "Alice"   -> "Long Cat",
    "Charlie" -> "Ceiling Cat",
    "Edith"   -> "Cloud Cat")


  def favoriteColorOption(name: String): Option[String] =
    favoriteColors.get(name)

  def favoriteColor(name: String): String =
    favoriteColors.getOrElse(name, "beige")

  def printColors(): Unit =
    for {
      person <- people
    } println(s"$person's favorite color is ${favoriteColor(person)}")

  def printColors2(): Unit =
    people.foreach {
      person => println(s"$person's favorite color is ${favoriteColor(person)}")
    }

  def lookup[A](name: String, lookupMap: Map[String, A]): Option[A] =
    lookupMap.get(name)

  def colorOfOldestPerson: String = {
    val oldestPerson = ages.fold("" -> Int.MinValue) {
      (olderPerson, currentPerson) =>
        if (currentPerson._2 > olderPerson._2)
          currentPerson
        else
          olderPerson
    }
    favoriteColor(oldestPerson._1)
  }
}
