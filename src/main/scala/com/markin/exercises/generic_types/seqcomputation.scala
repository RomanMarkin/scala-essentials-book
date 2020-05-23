package com.markin.exercises.generic_types

object MySeqComp extends App {
  val list = List(1, 2, 3)
  val newList = list.flatMap(e => List(e, -e))
  newList.foreach(println(_))

  val list2: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
  val newList2 = list2.map(maybe => maybe.flatMap[Int](y => if (y % 2 == 0) Full(y) else Empty()))
  newList2.foreach(println(_))
}