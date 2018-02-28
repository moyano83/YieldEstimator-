package com.smartrural.estimator.model

case class ColoredPixel(rgbColor:Int, row:Int, col:Int) {

  def this(red:Int, green:Int, blue:Int, row:Int, col:Int) {
    this(red * 65536 + green * 256 + blue, row, col)
  }


  val red = (rgbColor & 0xff0000) / 65536

  val green = (rgbColor & 0xff00) / 256

  val blue = (rgbColor & 0xff)

  def isVoid():Boolean = (red == 0) && (green ==0) && (blue == 0)

  def isNotVoid():Boolean = !isVoid()

  override def hashCode(): Int = blue

  override def canEqual(that: Any): Boolean = that match{
    case ColoredPixel(_, _, _) => true
    case _ => false
  }

  override def equals(obj: scala.Any): Boolean = canEqual(obj) &&
    obj.asInstanceOf[ColoredPixel].red == this.red &&
    obj.asInstanceOf[ColoredPixel].green == this.green &&
    obj.asInstanceOf[ColoredPixel].blue == this.blue &&
    obj.asInstanceOf[ColoredPixel].row == this.row &&
    obj.asInstanceOf[ColoredPixel].col == this.col
}
