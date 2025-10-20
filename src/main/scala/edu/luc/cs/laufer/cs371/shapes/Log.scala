package edu.luc.cs.laufer.cs371.shapes

import java.util.logging.{Level, Logger}

object Log:
  private val logger: Logger = Logger.getLogger("edu.luc.cs.laufer.cs371.shapes")

  private val levelName = System.getProperty("shapes.log.level", "WARNING")
  logger.setLevel(Level.parse(levelName))

  def fine(msg: => String): Unit =
    if logger.isLoggable(Level.FINE) then logger.fine(msg)
end Log
