package com.markin.exercises

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

sealed trait Rectangular extends Shape{
  def width: Double
  def length: Double
  override def sides: Int = 4
  override def perimeter: Double = 2 * (length + width)
  override def area: Double = length * width
}

case class Circle(radius: Double, color: Color) extends Shape{
  override def sides: Int = 1
  override def perimeter: Double = 2 * math.Pi * radius
  override def area: Double = math.Pi * radius * radius
}

case class Rectangle(width: Double, length: Double, color: Color) extends Rectangular

case class Square(width: Double, color: Color) extends Rectangular {
  val length: Double = width
}

object Draw {
  def apply(shape: Shape): String = shape match {
    case Circle(radius, color) => s"A ${Draw(color)} circle with radius $radius"
    case Rectangle(width, length, color) => s"A ${Draw(color)} rectangle with width $width and length $length"
    case Square(width, color) => s"A ${Draw(color)} square with width $width"
  }

  def apply(c: Color):String = c match {
    case Red => "red"
    case Yellow => "yellow"
    case Pink => "pink"
    case c => if (c.isLight) "light" else "dark"
  }
}

sealed trait Color {
  def r: Byte
  def g: Byte
  def b: Byte
  def isLight: Boolean = {
    // HSP (Highly Sensitive Poo) equation from http://alienryderflex.com/hsp.html
    val hsp: Double = math.sqrt(
      0.299 * (r * r) +
        0.587 * (g * g) +
        0.114 * (b * b)
    )
    // Using the HSP value, determine whether the color is light or dark
    hsp > 127.5
  }
  def isDark: Boolean = !isLight
}

final case class CustomColor(r: Byte, g: Byte, b: Byte) extends Color

case object Red extends Color {
  val r: Byte = 0xFF.toByte
  val g: Byte = 0
  val b: Byte = 0
}
case object Yellow extends Color {
  val r: Byte = 0xFF.toByte
  val g: Byte = 0xFF.toByte
  val b: Byte = 0
}
case object Pink extends Color {
  val r: Byte = 0xFF.toByte
  val g: Byte = 0x14.toByte
  val b: Byte = 0x93.toByte
}


object MyAppShapes extends App {
  assert(Square(3, CustomColor(0, 0, 0)).area == 9)
  assert(Rectangle(2,3, Red).perimeter == 10)
  assert(Circle(5, Yellow).sides == 1)

  println(Draw(Circle(10, Red)))
  println(Draw(Rectangle(7, 18, CustomColor(0xFF.toByte, 0, 0))))
  println(Draw(Square(7, CustomColor(0, 0, 0))))
}
