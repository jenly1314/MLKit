package com.king.mlkit.vision.segmentation

import com.google.mlkit.vision.segmentation.SegmentationMask
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer

/**
 * 自拍图像分割扫描Fragment
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class SegmentationCameraScanFragment : BaseCameraScanFragment<SegmentationMask>() {
    override fun createAnalyzer(): Analyzer<SegmentationMask>? = SegmentationAnalyzer()
}
