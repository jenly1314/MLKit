package com.king.mlkit.vision.object

import com.google.mlkit.vision.objects.DetectedObject
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.object.analyze.ObjectDetectionAnalyzer

abstract class ObjectCameraScanActivity : BaseCameraScanActivity<List<DetectedObject>>() {
    override fun createAnalyzer(): Analyzer<List<DetectedObject>>? = ObjectDetectionAnalyzer()
}
