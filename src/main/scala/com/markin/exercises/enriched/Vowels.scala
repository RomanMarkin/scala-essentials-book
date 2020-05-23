package com.markin.exercises.enriched

object Vowels {
  def numberOfVowels(str: String): Int =
  //str.filter(chr => Seq('a', 'o', 'e', 'i', 'y', 'u').contains(chr)).length
    str.filter(Seq('a', 'e', 'i', 'o', 'u').contains(_)).length
}


object StringImplicits {
  implicit class ExtraStringMethods(str: String) {
    val vowels = Seq('a', 'e', 'i', 'o', 'u')

    def numberOfVowels: Int = str.toList.count(vowels.contains(_))
  }
}


object VowelsMain {
  def main(args: Array[String]): Unit = {

    //Exercise #1
    assert(Vowels.numberOfVowels("the quick brown fox") == 5)

    //Exercise #2
    import StringImplicits._
    assert(new ExtraStringMethods("the quick brown fox").numberOfVowels == 5)

    //Exercise #3
    assert("the quick brown fox".numberOfVowels == 5)
  }
}
