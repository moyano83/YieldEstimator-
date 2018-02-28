package com.smartrural.estimator.service

import java.io.File

import com.smartrural.estimator.model.BBoxItemInfo

/**
  * Created by jm186111 on 31/01/2018.
  */
trait BoundingBoxService {
  /**
    * Reads the bboxFile provided
    * @param bboxFilePath the file to read
    * @return the Inferences extracted from the file
    */
  def readBBoxFile(bboxFilePath:File):Map[String, List[BBoxItemInfo]]

  /**
    * Gets the list of distinct images contained in the file
    * @param bboxFilePath the file to read
    * @return the list of distinct images
    */
  def getDistinctImages(bboxFilePath:File):(String, Set[String]) =
    (bboxFilePath.getParentFile.getName, readBBoxFile(bboxFilePath).keySet)
}
