package com.markin.exercises.sequence

case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

class FilmsDatabase {
  val memento = Film ("Memento", 2000, 8.5)
  val darkKnight = Film ("Dark Knight", 2008, 9.0)
  val inception = Film ("Inception", 2010, 8.8)

  val highPlainsDrifter = Film ("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = Film ("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = Film ("Unforgiven", 1992, 8.3)
  val granTorino = Film ("Gran Torino", 2008, 8.2)
  val invictus = Film ("Invictus", 2009, 7.4)

  val predator = Film ("Predator", 1987, 7.9)
  val dieHard = Film ("Die Hard", 1988, 8.3)
  val huntForRedOctober = Film ("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

  val mcTiernan = Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

  val nolan = Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))

  val someGuy = Director("Just", "Some Guy", 1990,
    Seq())

  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)
}

object SeqFilms1 extends FilmsDatabase {

  def directorsWithBackCatalogOfSize(numberOfFilms: Int): Seq[Director] =
    directors.filter(_.films.length > numberOfFilms)

  def directorBornBefore(year: Int): Option[Director] =
    directors.find(_.yearOfBirth < year)

  def directorBornBeforeWithBackCatalogOfSize(numberOfFilms: Int, year: Int): Seq[Director] =
    directors.filter(d =>
      d.films.length > numberOfFilms &&
        d.yearOfBirth < year)

  def directorBornBeforeWithBackCatalogOfSize2(numberOfFilms: Int, year: Int): Seq[Director] = {
    val byAge = directors.filter(_.yearOfBirth < year)
    val byFilms = directors.filter(_.yearOfBirth < year)
    byAge.filter(byFilms.contains)  //d => byFilms.contains(d)
  }


  def sortDirectorsByAge(ascending: Boolean = true): Seq[Director] =
    if (ascending)
      directors.sortWith(_.yearOfBirth < _.yearOfBirth)
    else
      directors.sortWith(_.yearOfBirth > _.yearOfBirth)


  /** Less effective as checks 'ascending' flag every time */
  def sortDirectorsByAge2(ascending: Boolean = true): Seq[Director] =
    directors.sortWith(
      if (ascending)
        _.yearOfBirth < _.yearOfBirth
      else
        _.yearOfBirth > _.yearOfBirth)

  def nolanFilmNames:Seq[String] =
    nolan.films.map(_.name)

  def allFilmNames:Seq[String] =
    directors.flatMap(_.films.map(_.name))

  def earliestFilmDateByMcTiernan:Int =
    mcTiernan.films.foldLeft(mcTiernan.films.head.yearOfRelease){
      (minYear, film) => math.min(minYear, film.yearOfRelease)
    }

  def sortedByImdb:Seq[Film] =
    directors.flatMap(_.films).sortWith(_.yearOfRelease > _.yearOfRelease)

  def averageScore:Double = {
    val allFilms = directors.flatMap(_.films)
    val totalRating = allFilms.foldLeft[Double](0){
      (ratingSum, film) => film.imdbRating + ratingSum
    }
    totalRating / allFilms.length
  }

  def announceFilms:Unit =
    directors.foreach { d =>
      d.films.foreach { f =>
        println(s"Tonight only! ${f.name} by ${d.firstName} ${d.lastName}")
      }
    }

  def earliestFilmByAnyDirector: Seq[Film] =
    directors.map { d =>
      d.films.foldLeft(d.films.head) {
        (earliestFilm, film) =>
          if (earliestFilm.yearOfRelease > film.yearOfRelease)
            film
          else
            earliestFilm
      }
    }

  def earliestFilmByAnyDirector2: Seq[Option[Film]] =
    directors.map { d =>
      d.films.foldLeft(d.films.headOption) {
        (earliestFilmOption, film) =>
          earliestFilmOption.flatMap(earliestFilm =>
            if (earliestFilm.yearOfRelease > film.yearOfRelease)
              Some(film)
            else
              earliestFilmOption
          )
      }
    }


  def earliestFilmByAnyDirector3: Seq[Option[Film]] = {
    directors.map { d =>
      d.films.sortWith {
        (f1, f2) =>
          f1.yearOfRelease < f2.yearOfRelease
      }.headOption
    }
  }

}

object ForComprehensionFilms extends FilmsDatabase {

  def nolanFilms:Seq[String] =
    for {
      fimd <- nolan.films
    } yield fimd.name

  def allFilmNames:Seq[String] =
    for {
      director <- directors
      film <- director.films
    } yield film.name

  def sortedByImdb:Seq[Film] = {
    val allFilms = for {
      director <- directors
      film <- director.films
    } yield film
    allFilms.sortWith(_.yearOfRelease > _.yearOfRelease)
  }

  def announceFilms:Unit =
    for {
      director <- directors
      film <- director.films
    } println(s"Tonight only! ${film.name} by ${director.firstName} ${director.lastName}")

}
