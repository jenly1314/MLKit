package com.king.mlkit.vision.segmentation

import com.google.mlkit.vision.segmentation.SegmentationMask
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer

abstract class SegmentationCameraScanActivity : BaseCameraScanActivity<SegmentationMask>() {
    override fun createAnalyzer(): Analyzer<SegmentationMask>? = SegmentationAnalyzer()
}
