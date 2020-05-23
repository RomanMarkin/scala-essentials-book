package com.markin.exercises.binarytree.v1

sealed trait Tree {
  def sum: Int = this match {
    case Leaf(value) => value
    case Node(left, right) => left.sum + right.sum
  }

  def double: Tree = this match {
    case Leaf(value) => Leaf(value * 2)
    case Node(left, right) => Node(left.double, right.double)
  }
}
final case class Node(left: Tree, right: Tree) extends Tree
final case class Leaf(value: Int) extends Tree

object MyAppBinaryTree extends App {
  val tree = Node(
    Node(
      Node(
        Leaf(3),
        Leaf(7)),
      Node(
        Leaf(10),
        Leaf(0))),
    Node(
      Node(
        Leaf(1),
        Leaf(2)),
      Node(
        Node(
          Leaf(3),
          Leaf(4)),
        Leaf(5))))

  val treeDoubled = Node(
    Node(
      Node(
        Leaf(6),
        Leaf(14)),
      Node(
        Leaf(20),
        Leaf(0))),
    Node(
      Node(
        Leaf(2),
        Leaf(4)),
      Node(
        Node(
          Leaf(6),
          Leaf(8)),
        Leaf(10))))

  assert(tree.sum == (3 + 7 + 10 + 0 + 1 + 2 + 3 + 4 + 5))
  assert(tree.double == treeDoubled)
}