package edu.luc.cs.laufer.cs371.shapes

import Shape.*
import Log.*

/**
 * boundingBox behavior for all shapes.
 * Returns a Location(x, y, Rectangle(w, h)) representing
 * the top-left corner and dimensions of the box.
 *
 * Functional style: uses map, foldLeft, and min/max.
 */
object boundingBox:
  def apply(s: Shape): Location = s match

    // A rectangle’s bounding box is itself at (0,0)
    case Rectangle(w, h) =>
      fine(s"bbox(Rectangle($w,$h)) -> (0,0,$w,$h)")
      Location(0, 0, Rectangle(w, h))

    // Ellipse centered at origin: bounding box spans (-rx,-ry) to (rx,ry)
    case Ellipse(rx, ry) =>
      fine(s"bbox(Ellipse($rx,$ry)) -> (-$rx,-$ry, ${2 * rx}, ${2 * ry})")
      Location(-rx, -ry, Rectangle(2 * rx, 2 * ry))

    // Location shifts the inner bounding box
    case Location(x, y, inner) =>
      val Location(x0, y0, Rectangle(w, h)) = apply(inner)
      val newX = x + x0
      val newY = y + y0
      fine(s"bbox(Location($x,$y,...)) -> ($newX,$newY,$w,$h)")
      Location(newX, newY, Rectangle(w, h))

    // Empty group (no shapes)
    case Group(children*) if children.isEmpty =>
      fine("bbox(empty group) -> (0,0,0,0)")
      Location(0, 0, Rectangle(0, 0))

    // Group: combine all children via map, foldLeft, min/max
    case Group(children*) =>
      val boxes = children.map(apply)
      val (minX, minY, maxX, maxY) =
        boxes.foldLeft((Int.MaxValue, Int.MaxValue, Int.MinValue, Int.MinValue)) {
          case ((mnx, mny, mxx, mxy), Location(x, y, Rectangle(w, h))) =>
            val right = x + w
            val bottom = y + h
            (math.min(mnx, x), math.min(mny, y), math.max(mxx, right), math.max(mxy, bottom))
        }
      val width = maxX - minX
      val height = maxY - minY
      fine(s"bbox(Group) -> ($minX,$minY,$width,$height)")
      Location(minX, minY, Rectangle(width, height))
end boundingBox
