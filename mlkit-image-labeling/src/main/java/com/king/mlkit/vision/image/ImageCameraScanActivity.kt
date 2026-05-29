package com.king.mlkit.vision.image

import com.google.mlkit.vision.label.ImageLabel
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.image.analyze.ImageLabelingAnalyzer

abstract class ImageCameraScanActivity : BaseCameraScanActivity<List<ImageLabel>>() {
    override fun createAnalyzer(): Analyzer<List<ImageLabel>>? = ImageLabelingAnalyzer()
}
