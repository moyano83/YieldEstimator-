package com.smartrural.estimator.transformer

import java.awt.image.BufferedImage

/**
  * Created by jm186111 on 12/02/2018.
  */
trait ImageTransformer {
  /**
    * Transform an imagen according to the internal implementation of the Transformer
    * @param img the image to transform
    * @return the transformed image
    */
  def transform(img:BufferedImage):BufferedImage

  /**
    * Gets a void image with the same size than the original one
    * @param img the image to base the copy to
    * @param isCopy returns a copy if the image if this is true, otherwise returns a blank canvas
    * @return the bufferedImage
    */
  def getImageCanvas(img:BufferedImage, isCopy:Boolean):BufferedImage =
    if(isCopy){
      val cm = img.getColorModel()
      val isAlphaPremultiplied = cm.isAlphaPremultiplied()
      val raster = img.copyData(null)
      new BufferedImage(cm, raster, isAlphaPremultiplied, null)
    }else{
      new BufferedImage(img.getWidth, img.getHeight, BufferedImage.TYPE_INT_RGB)
    }
}
