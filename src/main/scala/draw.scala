package edu.luc.cs.laufer.cs371.shapes

import java.awt.{BasicStroke, Color, Graphics2D, RenderingHints}
import java.awt.image.BufferedImage

/** Extra-credit: recursive rendering of shapes to a Graphics2D context. */
object draw:

  /** Render a Shape recursively onto the given Graphics2D. */
  def apply(s: Shape, g: Graphics2D): Unit = s match
    case Shape.Rectangle(w, h) =>
      g.drawRect(0, 0, w, h)

    case Shape.Ellipse(rx, ry) =>
      g.drawOval(-rx, -ry, 2 * rx, 2 * ry)

    case Shape.Location(x, y, inner) =>
      val old = g.getTransform
      g.translate(x.toDouble, y.toDouble)
      apply(inner, g)
      g.setTransform(old)

    case Shape.Group(children*) =>
      children.foreach(ch => apply(ch, g))

  /** Create a BufferedImage and draw the given shape into it. */
  def renderToImage(s: Shape, width: Int = 800, height: Int = 600): BufferedImage =
    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g = img.createGraphics()
    g.setColor(Color.black)
    g.setStroke(new BasicStroke(2))
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    apply(s, g)
    g.dispose()
    img
end draw
