package com.markin.exercises


case class Person(firstName : String, lastName : String) {
  def name: String = s"$firstName $lastName"
}

object Person {
  def apply(fullName: String): Person = {
    val nameParts = fullName.split(" ")
    new Person(nameParts(0), nameParts(1))
  }
}

object MyAppPerson extends App {
  println(Person.apply("John Snow").name)
  println(Person("Joe White").name)
}
