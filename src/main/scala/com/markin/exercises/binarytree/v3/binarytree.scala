package com.markin.exercises.binarytree.v3

sealed trait Tree[A]{
  def fold[B](leaf: A => B, node: (B, B) => B ): B = this match {
    case Leaf(value) => leaf(value)
    case Node(l, r) => node(l.fold(leaf, node), r.fold(leaf, node))
  }
}

final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]


object MyAppBinaryTreeV3 extends App {
  val tree: Tree[String] =
    Node(Node(Leaf("To"), Leaf("iterate")),
      Node(Node(Leaf("is"), Leaf("human,")),
        Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

  val s = tree.fold[String](v => v, (l, r) => s"$l $r")
  println(s)
}

