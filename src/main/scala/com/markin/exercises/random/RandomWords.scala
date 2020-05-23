package com.markin.exercises.random


object RandomWords {

  val subjects = List("Noel", "The cat", "The dog")
  val verbs = List("wrote", "chased", "slept on")
  val objects = List("the book", "the ball", "the bed")


  val verbs2 = Map(
    "Noel" -> List("wrote", "chased", "slept on"),
    "The cat" -> List("meowed at", "chased", "slept on"),
    "The dog" -> List("barked at", "chased", "slept on")
  )

  val objects2 = Map(
    "wrote" -> List("the book", "the letter", "the code"),
    "chased" -> List("the ball", "the dog", "the cat"),
    "slept on" -> List("the bed", "the mat", "the train"),
    "meowed at" -> List("Noel", "the door", "the food cupboard"),
    "barked at" -> List("the postman", "the car", "the cat")
  )

  def writeAllPossibleSentences: List[(String, String, String)] =
    for {
      subj <- subjects
      verb <- verbs
      obj <- objects
    } yield (subj, verb, obj)

  def writeAllPossibleSentences2: List[(String, String, String)] =
    for {
      subj <- subjects
      verb <- verbs2.getOrElse(subj, List())
      obj <- objects2.getOrElse(verb, List())
    } yield (subj, verb, obj)

  def main(args: Array[String]): Unit = {

    writeAllPossibleSentences.foreach {
      entry =>
        val (subj, verb, obj) = entry
        println(s"$subj $verb $obj")
    }

    println("----------------------")

    writeAllPossibleSentences2.foreach {
      entry =>
        val (subj, verb, obj) = entry
        println(s"$subj $verb $obj")
    }
  }
}
