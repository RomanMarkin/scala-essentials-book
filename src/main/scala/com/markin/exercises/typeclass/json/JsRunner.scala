package com.markin.exercises.typeclass.json

import java.util.Date

object JsRunner {
  def main(args: Array[String]): Unit = {

    //1.

    val obj = JsObject(Map(
      "foo" -> JsString("a"),
      "bar" -> JsString("b"),
      "baz" -> JsString("c"))
    )

    println(obj.stringify)


    //2.
    val visitors: Seq[Visitor] = Seq(
      Anonymous("001", new Date),
      User("003", "dave@xample.com", new Date))

    import VisitorJsonConverters._
    import jsutil._

    //println(visitors.map(JsUtil.toJson(_).stringify))

    println(visitors.map(_.toJson.stringify))
  }
}
