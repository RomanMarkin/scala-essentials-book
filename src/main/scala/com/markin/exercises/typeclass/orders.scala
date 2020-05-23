package com.markin.exercises.typeclass

final case class Order(units: Int, unitPrice: Double){
  val totalPrice = units * unitPrice
}

object TotalPriceOrdering {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.totalPrice < _.totalPrice)
}

object UnitsNumberOrdering {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.units < _.units)
}

object UnitPriceOrdering {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.unitPrice < _.unitPrice)
}


object Orders{
  def main(args: Array[String]): Unit = {
    import TotalPriceOrdering._
    assert(List(Order(1, 2), Order(2, 5), Order(1, 6)).sorted == List(Order(1, 2), Order(1, 6), Order(2, 5)))
  }
}