package com.markin.exercises.sequence

object SeqAnimals extends App {
  val animals: Seq[String] = Seq("cat", "dog", "penguin")
  val animals2: Seq[String] = "mouse" +: animals :+ "tyrannosaurus"
  val animals3: Seq[Any] = 2 +: animals2
}
