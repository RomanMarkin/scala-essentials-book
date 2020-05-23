package com.markin.exercises.patternmatching

object TitleCase {
  def unapply(s: String): Option[String] = {
    Some(
      s.split(" ").map {
        case ""   => ""
        case word => word.substring(0, 1).toUpperCase() + word.substring(1)
      }.mkString(" ")
    )
  }
}

object TitleCaseExtractorTester {
  def main(args: Array[String]): Unit = {
    assert(
      "Sir Lord Doctor David Gurnell A" ==
        ("sir lord doctor david gurnell a" match {
          case TitleCase(str) => str
        })
    )
  }
}
