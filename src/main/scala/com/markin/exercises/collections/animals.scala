package com.markin.exercises.collections

import scala.collection.mutable

object AnimalsObject {

  def main(args: Array[String]): Unit = {
    // 1
    val animals: Seq[String] = Seq[String]("cat", "dog", "penguin")
    val animals3: Seq[String] = "mouse" +:  animals :+ "tyrannosaurus"

    animals3.foreach(println)

    // 2
    val animals4: Seq[Any] = animals3 :+ 2
    println(animals4.getClass)
    animals4.foreach(println)

    // 3
    val animals5: mutable.Seq[String] = mutable.Seq[String]("cat", "dog", "penguin")
    println(animals5.getClass)
    animals5.foreach(println)

    animals5(1) = 2.toString
    animals5.foreach(println)
  }
}
