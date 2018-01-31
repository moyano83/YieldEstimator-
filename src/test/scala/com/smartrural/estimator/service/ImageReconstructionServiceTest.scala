package com.smartrural.estimator.service

import java.io.File
import javax.imageio.ImageIO

import com.smartrural.estimator.service.impl.{BoundingBoxTextReaderService, LocalImageReconstructionService}
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import scaldi.Module


@RunWith(classOf[JUnitRunner])
class ImageReconstructionServiceTest extends FlatSpec{

  val rootPath = getClass.getClassLoader.getResource(".").getPath
  val partition = "valdemonjas-2017-09-13_01"
  val patchesFolder = new File(new File(rootPath), s"inferences/${partition}")
  val originalImagesFolder =  new File(rootPath.concat(s"original_images/${partition}"))
  val bboxesFolder =  new File(rootPath.concat(s"inferences_info/${partition}"))
  val imageName = "z-img-000-000004.jpg"

  implicit val inj = new Module{
    bind[BoundingBoxService] to new BoundingBoxTextReaderService
  }
  val imageReconstructionService = new LocalImageReconstructionService()

  behavior of "ImageReconstructionService"

  it should "find the related images within the inferences folder" in {
    val bufferedImageArray = imageReconstructionService.retrievePatchesForImage(patchesFolder.getPath, imageName)
    assert(bufferedImageArray.size == 7)
    assert(bufferedImageArray(0).getWidth == 91)
    assert(bufferedImageArray(0).getHeight == 145)
    assert(bufferedImageArray(1).getWidth == 57)
    assert(bufferedImageArray(1).getHeight == 65)
    assert(bufferedImageArray(2).getWidth == 63)
    assert(bufferedImageArray(2).getHeight == 111)
    assert(bufferedImageArray(3).getWidth == 31)
    assert(bufferedImageArray(3).getHeight == 53)
    assert(bufferedImageArray(4).getWidth == 97)
    assert(bufferedImageArray(4).getHeight == 132)
    assert(bufferedImageArray(5).getWidth == 61)
    assert(bufferedImageArray(5).getHeight == 61)
    assert(bufferedImageArray(6).getWidth == 59)
    assert(bufferedImageArray(6).getHeight == 52)
  }

  it should "reconstruct the final image from the available patches" in {
    val partition = "valdemonjas-2017-09-13_01"
    val destinationFolder = new File(rootPath, "results")
    val imageFile = new File(originalImagesFolder, imageName)

    imageReconstructionService.reconstructImage(imageFile, bboxesFolder, patchesFolder, destinationFolder)
    assert(new File(new File(destinationFolder, partition), imageName).exists())
  }

  it should "recreate the binary image from the patches" in {

  }

  it should "write the pixel with the specified value" in {

  }

  it should "not write the pixel if it is void" in {

  }
}







