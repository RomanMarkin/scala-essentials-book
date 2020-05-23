package com.markin.exercises.typeclass.json

import java.util.Date
import jsutil._

// Instances of Type Class 'JsWriter'
object VisitorJsonConverters {

  implicit object StringJsWriter extends JsWriter[String] {
    override def write(s: String): JsValue = JsString(s)
  }

  implicit object DateJsWriter extends JsWriter[Date] {
    override def write(d: Date): JsValue = JsString(d.toString)
  }

  implicit object LongJsWriter extends JsWriter[Long] {
    override def write(l: Long): JsValue = JsString(l.toString)
  }

  implicit object AnonymousJsWriter extends JsWriter[Anonymous] {
    override def write(anon: Anonymous): JsValue = JsObject(Map(
      "id" -> anon.id.toJson,
      "createdAt" -> anon.createdAt.toJson,
      "age" -> anon.age.toJson)
    )
  }

  implicit object UserJsWriter extends JsWriter[User] {
    override def write(user: User): JsValue = JsObject(Map(
      "id" -> user.id.toJson,
      "email" -> user.email.toJson,
      "createdAt" -> user.createdAt.toJson,
      "age" -> user.age.toJson)
    )
  }

  implicit object VisitorJsWriter extends JsWriter[Visitor] {
    override def write(a: Visitor): JsValue = a match {
      case anon: Anonymous => anon.toJson
      case user: User => user.toJson
    }
  }

}
