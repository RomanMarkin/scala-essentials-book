package com.markin.exercises.binarytree.v2

sealed trait Tree {
  def sum: Int
  def double: Tree
}
final case class Node(left: Tree, right: Tree) extends Tree {
  def sum: Int = left.sum + right.sum
  def double: Tree = Node(left.double, right.double)
}
final case class Leaf(value: Int) extends Tree {
  def sum: Int = value
  def double: Tree = Leaf(value * 2)
}

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