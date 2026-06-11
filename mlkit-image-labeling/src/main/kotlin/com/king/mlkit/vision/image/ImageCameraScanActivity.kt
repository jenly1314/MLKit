package com.king.mlkit.vision.image

import com.google.mlkit.vision.label.ImageLabel
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.image.analyze.ImageLabelingAnalyzer

/**
 * 图像标签扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class ImageCameraScanActivity : BaseCameraScanActivity<List<ImageLabel>>() {
    override fun createAnalyzer(): Analyzer<List<ImageLabel>>? = ImageLabelingAnalyzer()
}
