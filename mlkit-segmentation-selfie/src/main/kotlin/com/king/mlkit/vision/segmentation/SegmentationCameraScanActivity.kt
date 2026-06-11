package com.king.mlkit.vision.segmentation

import com.google.mlkit.vision.segmentation.SegmentationMask
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer

/**
 * 自拍图像分割扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class SegmentationCameraScanActivity : BaseCameraScanActivity<SegmentationMask>() {
    override fun createAnalyzer(): Analyzer<SegmentationMask>? = SegmentationAnalyzer()
}
