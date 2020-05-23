package com.markin.exercises.typeclass

trait Equal[A] {
  def equal(a1: A, a2: A): Boolean
}

object Equal {
  def apply[A](implicit equal: Equal[A]): Equal[A] = equal

  implicit class ToEqual[A](in: A) {
    def ===(compareTo: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(in, compareTo)
  }

}

case class Person(name: String, email: String)

object PersonImplicits {

  implicit object NameEqual extends Equal[Person] {
    override def equal(a1: Person, a2: Person): Boolean = a1.name == a2.name
  }

  implicit object NameEmailEqual extends Equal[Person] {
    override def equal(a1: Person, a2: Person): Boolean = a1.name == a2.name && a1.email == a2.email
  }

}


object Eq {
  def apply[A](a1: A, a2: A)(implicit equal: Equal[A]): Boolean = equal.equal(a1, a2)
}

object EqualPerson {
  def main(args: Array[String]): Unit = {
    import PersonImplicits.NameEmailEqual

    //Example #1
    val eq1 = Eq[Person](Person("Ivan", "no@mail.com"), Person("Ivan", "yes@mail.com"))
    println(s"Equal1: $eq1")

    //Example #2
    val eq2 = Equal[Person].equal(Person("Ivan", "no@mail.com"), Person("Ivan", "yes@mail.com"))
    println(s"Equal2: $eq2")
  }
}
