package com.markin.exercises

object PMBudget {
  type Role = String

  val roleHoursInTeamHour: Map[Role, Double] = Map(
    "PM" -> 0.1,
    "DEV" -> 1.0,
    "QA" -> 1.0
  )

  val roleHourInBaseHours: Map[Role, Double] = Map(
    "PM" -> 2.5,
    "DEV" -> 4.0,
    "QA" -> 1.0
  )

  def roleHoursFromTeamHours(role: Role, teamHours: Double): Double =
    roleHoursInTeamHour.getOrElse(role, 0) * teamHours

  def roleHoursToBaseHours(role: Role, roleHours: Double): Double =
    roleHourInBaseHours.get(role).map(_ * roleHours).getOrElse(0)

  def teamHoursToBaseHours(teamHours: Double): Double = {
    val v = for {
      rh <- roleHoursInTeamHour
      bh <- roleHourInBaseHours.filter(_._1 == rh._1)
    } yield rh._2 * bh._2 * teamHours
    v.sum
  }

  def main(args: Array[String]): Unit = {
    val teamHours = 100
    val baseHours = roleHoursInTeamHour("PM") * roleHourInBaseHours("PM") * teamHours +
      roleHoursInTeamHour("DEV") * roleHourInBaseHours("DEV") * teamHours +
      roleHoursInTeamHour("QA") * roleHourInBaseHours("QA") * teamHours

    assert(teamHoursToBaseHours(teamHours) == baseHours)

    println(s"Team hours of $teamHours equals to Base hours of $baseHours")
  }

}
