package com.king.mlkit.vision.`object`

import com.google.mlkit.vision.objects.DetectedObject
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.`object`.analyze.ObjectDetectionAnalyzer

/**
 * 对象检测扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class ObjectCameraScanActivity : BaseCameraScanActivity<List<DetectedObject>>() {
    override fun createAnalyzer(): Analyzer<List<DetectedObject>>? = ObjectDetectionAnalyzer()
}
