package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

/** Extra credit: tests for draw behavior. */
class TestDraw extends AnyFunSuite:

  test("draw simple rectangle to image file") {
    val shape = Shape.Rectangle(100, 50)
    val image = draw.renderToImage(shape, 200, 200)
    assert(image.getWidth === 200)
    assert(image.getHeight === 200)

    // Optional output file for visual check
    val outputFile = File("rectangle_test_output.png")
    ImageIO.write(image, "png", outputFile)
    assert(outputFile.exists())
  }

  test("draw nested Location") {
    val shape = Shape.Location(60, 40, Shape.Rectangle(50, 30))
    val image = draw.renderToImage(shape)
    assert(image != null)
  }
end TestDraw
