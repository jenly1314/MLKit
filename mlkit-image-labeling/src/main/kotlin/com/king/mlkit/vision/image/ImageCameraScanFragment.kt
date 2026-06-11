package com.king.mlkit.vision.image

import com.google.mlkit.vision.label.ImageLabel
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.image.analyze.ImageLabelingAnalyzer

/**
 * 图像标签扫描Fragment
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class ImageCameraScanFragment : BaseCameraScanFragment<List<ImageLabel>>() {
    override fun createAnalyzer(): Analyzer<List<ImageLabel>>? = ImageLabelingAnalyzer()
}
