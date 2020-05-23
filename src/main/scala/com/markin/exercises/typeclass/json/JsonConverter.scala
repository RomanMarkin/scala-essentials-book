package com.markin.exercises.typeclass.json

// Algebraic Data Type (Sum Type)
sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  override def stringify: String =
    values
      .map[String] {
        case (name, value) => "\"" + name + "\": " + value.stringify
      }
      .mkString("{", ", ", "}")
}

final case class JsString(value: String) extends JsValue {
  override def stringify: String =
    "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

// Type Class for converting Scala data to JSON
trait JsWriter[A] {
  def write(a: A): JsValue
}

// Combining (1) Type Enrichment (via Implicit Class) with (2) Interface to access Type Class method(s)
object jsutil {
  implicit class JsUtil[A](a: A) {
    def toJson(implicit writer: JsWriter[A]): JsValue = writer.write(a)
  }
}
