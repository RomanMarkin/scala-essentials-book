package com.markin.exercises

// Exercise 3.1.6.3 Directorial Debut
case class Director(firstName: String,
               lastName: String,
               yearOfBirth: Int) {
  def name: String = s"$firstName $lastName"
}

object Director {

  def older(director1: Director,
            director2: Director): Director = if (director1.yearOfBirth > director2.yearOfBirth) director1 else director2
}

case class Film(name: String,
           yearOfRelease: Int,
           imdbRating: Double,
           director: Director) {

  def directorsAge = yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director) = director == this.director

}

object Film {
  def apply(name: String,
            yearOfRelease: Int,
            imdbRating: Double,
            director: Director): Film = new Film(name, yearOfRelease, imdbRating, director)

  def highestRating(film1: Film,
                    film2: Film) : Double =
    if (film1.imdbRating > film2.imdbRating) film1.imdbRating else film2.imdbRating

  def oldestDirectorAtTheFilm(film1: Film,
                    film2: Film) : Director = {
    val f1 = film1.yearOfRelease - film1.director.yearOfBirth
    val f2 = film2.yearOfRelease - film2.director.yearOfBirth
    if (f1 > f2) film1.director else film2.director
  }

}


object Dad {
  def rate(film: Film): Double = film match {
    case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
    case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
    case _ => 3.0
  }
}


object MyAppFilms extends App {
  // Exercise 3.1.6.3 Directorial Debut

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "Some Body", 1990)

  val memento = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception = new Film("Inception", 2010, 8.8, nolan)

  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
  val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus = new Film("Invictus", 2009, 7.4, eastwood)

  val predator = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

  assert(eastwood.yearOfBirth == 1930)
  assert(dieHard.director.name == "John McTiernan")
  assert(!invictus.isDirectedBy(nolan))

  highPlainsDrifter.copy(name = "L'homme des hautes plaines")

  thomasCrownAffair.copy(yearOfRelease = 1968,
    director = new Director("Norman", "Jewison", 1926))

  inception.copy().copy().copy()

  assert(Dad.rate(highPlainsDrifter) == 10)
  assert(Dad.rate(memento) == 3)
  assert(Dad.rate(predator) == 7)
  assert(Dad.rate(dieHard) != 10)

}