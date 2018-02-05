package com.smartrural.estimator.runner

import java.io.File

import com.smartrural.estimator.service.{FileManagerService, ImageReconstructionService}
import scaldi.{Injectable, Injector}

class ImageReconstructionRunner(bboxesPath:String,
                                originalImagesPath:String,
                                patchImgPath:String,
                                destinationPath:String)(implicit inj:Injector) extends Injectable with Runner{

  val imageReconstructionService = inject[ImageReconstructionService]

  val fileManagerService = inject[FileManagerService]
  override def run() = fileManagerService
      .getChildList(originalImagesPath)
      .map(partition => reconstructImagesPerPartition(partition.getName))
      .reduce(_ & _)

  def reconstructImagesPerPartition(partition:String):Boolean = fileManagerService
    .getChildList(new File(originalImagesPath, partition).getAbsolutePath)
    .map(image => imageReconstructionService.reconstructImage(
      image, new File(bboxesPath, partition), new File(patchImgPath, partition), new File(destinationPath, partition)
    )).reduce(_ & _)

}
