package com.markin.exercises.enriched

import com.markin.exercises.typeclass.Equal
import com.markin.exercises.typeclass.Equal.ToEqual

object EqualRunner {


  implicit val caseInsensitiveEquals: Equal[String] = /*new Equal[String] {
    override def equal(s1: String, s2: String): Boolean = s1.toLowerCase == s2.toLowerCase
  }
  Or converted in to Single Abstract Method:
  */ (s1: String, s2: String) => s1.toLowerCase == s2.toLowerCase

  def main(args: Array[String]): Unit = {
    assert("ABCD".===("abcd"))

  }
}
