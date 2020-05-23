package com.markin.exercises.maps

object UnionExample {

  def unionOfSets[A](set1: Set[A], set2: Set[A]): Set[A] =
    set2.foldLeft(set1){
      (union, el) => union + el
    }

  def unionOfMaps[K, V](map1: Map[K, V], map2: Map[K, V])(implicit sum: (V, V) => V): Map[K, V] =
    map1.foldLeft(map2) {
      (union, pair) => {
        val (k, v) = pair
        val summedV = union.get(k).map(v2 => sum(v, v2)).getOrElse(v)
        union + (k -> summedV)
      }
    }


  def main(args: Array[String]): Unit = {
    val map1 = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = Map("b" -> 2, "c" -> 3, "d" -> 4)
    val map3 = Map("a" -> 1, "b" -> 4, "c" -> 6, "d" -> 4)
    val map4 = Map("a" -> 2, "b" -> 4, "c" -> 6)
    val map5 = Map("e" -> 5)
    val map6 = Map("a" -> 1, "b" -> 2, "c" -> 3, "e" -> 5)
    val map7 = Map("a" -> 2, "b" -> 4, "c" -> 6, "e" -> 5)

    implicit val sumInt: (Int, Int) => Int =
      (val1: Int, val2: Int) =>
        val1 + val2

    assert(unionOfMaps(map1, map2) == map3)
    assert(unionOfMaps(map2, map1) == map3)
    assert(unionOfMaps(map1, map1) == map4)
    assert(unionOfMaps(map1, map5) == map6)
    assert(unionOfMaps(Map[String, Int](), map5) == map5)
    assert(unionOfMaps(map5, Map[String, Int]()) == map5)
    assert(unionOfMaps(Map[String, Int](), Map[String, Int]()) == Map[String, Int]())
    assert(unionOfMaps(map1, map2) == map3)
    assert(unionOfMaps(map1, map6) == map7)
    assert(unionOfMaps(map6, map1) == map7)
  }

}
