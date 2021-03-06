package com.smartrural.estimator.transformer.impl

import java.io.File

import com.smartrural.estimator.transformer.ImageTransformer
import org.opencv.core.{Mat, Size}
import org.opencv.imgproc.Imgproc

/**
  * Average blur filter transformer
  * @param radius the radius of the transformation
  */
class AvgBlurFilterTransformer(radius:Int) extends ImageTransformer{
  /**
    * @inheritdoc
    */
  override def transform(matFile:File, img:Mat):Mat = {
    Imgproc.blur(img, img, new Size(radius, radius))
    img
  }
}
