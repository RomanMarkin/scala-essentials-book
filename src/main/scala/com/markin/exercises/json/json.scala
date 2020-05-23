package com.markin.exercises.json

//  Json ::= JsString value:String
//         | JsBoolean value:Boolean
//         | JsNumber value:Double
//         | JsNull
//         | JsSequence
//         | JsObject
//  JsSequence ::= SeqCell head:Json tail:JsSequence | SeqEnd
//  JsObject ::= ObjectCell key:String value:Json tail:JsObject | ObjectEnd

sealed trait Json {

  /** My initial implementation. Works wrongly: doesn't handle nested JsSequences well */
  def print1(json: Json, prevJson: Json = JsNull): String = json match {
    case JsString(value) => s"""\"$value\""""
    case JsBoolean(value) => value.toString
    case JsNumber(value) => value.toString
    case JsNull => "null"
    case SeqCell(head, tail) =>
      val str = tail match {
        case SeqCell(_, _) => s"${print1(head, json)}, ${print1(tail, json)}"
        case SeqEnd => s"${print1(head, json)}]"
      }
      val headBracket = prevJson match {
        case SeqCell(_, _) => ""
        case _ => "["
      }
      headBracket + str
    case SeqEnd => "[]"
    case ObjectCell(key, value, tail) =>
      val str = tail match {
        case ObjectCell(_, _, _) => s"""\"$key\": ${print1(value, JsNull)}, ${print1(tail, json)}"""
        case ObjectEnd => s"""\"$key\": ${print1(value, JsNull)}}"""
      }
      val headBracket = prevJson match {
        case ObjectCell(_, _, _) => ""
        case _ => "{"
      }
      headBracket + str
    case ObjectEnd => "{}"
  }

  /** Copy of implementation from the book */
  def print2: String = {
    def quote(s: String): String =
      '"'.toString ++ s ++ '"'.toString

    def seqToJson(seq: SeqCell): String =
      seq match {
        case SeqCell(h, t@SeqCell(_, _)) =>
          s"${h.print2}, ${seqToJson(t)}"
        case SeqCell(h, SeqEnd) => h.print2
      }

    def objectToJson(obj: ObjectCell): String =
      obj match {
        case ObjectCell(k, v, t@ObjectCell(_, _, _)) =>
          s"${quote(k)}: ${v.print2}, ${objectToJson(t)}"
        case ObjectCell(k, v, ObjectEnd) =>
          s"${quote(k)}: ${v.print2}"
      }

    this match {
      case JsNumber(v) => v.toString
      case JsString(v) => quote(v)
      case JsBoolean(v) => v.toString
      case JsNull => "null"
      case s@SeqCell(_, _) => "[" ++ seqToJson(s) ++ "]"
      case SeqEnd => "[]"
      case o@ObjectCell(_, _, _) => "{" ++ objectToJson(o) ++ "}"
      case ObjectEnd => "{}"
    }
  }

  /** My second implementation after analysis of the solutions from the book*/
  def print3: String = {

    def quotes(str: String): String =
      '"'.toString ++ str ++ '"'.toString

    def jsSeqToString(seq: SeqCell): String = seq match {
      case SeqCell(head, tail@SeqCell(_, _)) => s"${head.print3}, ${jsSeqToString(tail)}"
      case SeqCell(head, SeqEnd) => head.print3
    }

    def jsObjToString(obj: ObjectCell): String = obj match {
      case ObjectCell(k, v, tail@ObjectCell(_, _, _)) => s"${quotes(k)}: ${v.print3}, ${jsObjToString(tail)}"
      case ObjectCell(k, v, ObjectEnd) => s"${quotes(k)}: ${v.print3}"
    }

    this match {
      case JsString(v) => quotes(v)
      case JsNumber(v) => v.toString
      case JsBoolean(v) => v.toString
      case JsNull => "null"
      case seq@SeqCell(_, _) => "[" + jsSeqToString(seq) + "]"
      case SeqEnd => "[]"
      case obj@ObjectCell(_, _, _) => "{" + jsObjToString(obj) + "}"
      case ObjectEnd => "{}"
    }
  }
}
final case class JsString(value: String) extends Json
final case class JsBoolean(value: Boolean) extends Json
final case class JsNumber(value: Double) extends Json
case object JsNull extends Json

sealed trait JsSequence extends Json
final case class SeqCell(head: Json, tail:JsSequence) extends JsSequence
case object SeqEnd extends JsSequence

sealed trait JsObject extends Json
final case class ObjectCell(key: String, value: Json, tail: JsObject) extends JsObject
case object ObjectEnd extends JsObject


object MyAppJson extends App {
  val json1 = SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqCell(SeqCell(SeqEnd, SeqEnd), SeqEnd))))
  val json2 =  ObjectCell("a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber(3.0), SeqEnd))),
            ObjectCell("b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell(JsString("c"), SeqEnd))),
            ObjectCell("c",
              ObjectCell("doh",
                JsBoolean(true),
                ObjectCell("ray",
                JsBoolean(false),
                ObjectCell("me",
                JsNumber(1.0),
                ObjectEnd))),
            ObjectEnd
      )
    )
  )

  println(json1.print1(json1))
  println(json1.print2)
  println(json1.print3)

  println(json2.print1(json2))
  println(json2.print2)
  println(json2.print3)
}