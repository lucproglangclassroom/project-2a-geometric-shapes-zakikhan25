package edu.luc.cs.laufer.cs371.shapes

/**
 * Algebraic Data Type (ADT) for all shapes.
 * Demonstrates object-oriented and functional programming in Scala.
 */
enum Shape derives CanEqual:

  /** A rectangle defined by its width and height. */
  case Rectangle(width: Int, height: Int)

  /** An ellipse centered at the origin, with x and y radii. */
  case Ellipse(rx: Int, ry: Int)

  /** A translated shape, shifted by (x, y) from the origin. */
  case Location(x: Int, y: Int, shape: Shape)

  /** A group (composite) containing zero or more shapes. */
  case Group(shapes: Shape*)

object Shape:

  /** Validated rectangle constructor. */
  def rectangle(width: Int, height: Int): Rectangle =
    require(width >= 0 && height >= 0,
      s"Rectangle dimensions must be non-negative: width=$width, height=$height")
    Rectangle(width, height)

  /** Validated ellipse constructor. */
  def ellipse(rx: Int, ry: Int): Ellipse =
    require(rx >= 0 && ry >= 0,
      s"Ellipse radii must be non-negative: rx=$rx, ry=$ry")
    Ellipse(rx, ry)

  /** Location constructor helper. */
  def location(x: Int, y: Int, s: Shape): Location =
    Location(x, y, s)

  /** Group constructor helper. */
  def group(items: Shape*): Group =
    Group(items*)
