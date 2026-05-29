package com.king.mlkit.vision.image

import com.google.mlkit.vision.label.ImageLabel
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.image.analyze.ImageLabelingAnalyzer

abstract class ImageCameraScanFragment : BaseCameraScanFragment<List<ImageLabel>>() {
    override fun createAnalyzer(): Analyzer<List<ImageLabel>>? = ImageLabelingAnalyzer()
}
