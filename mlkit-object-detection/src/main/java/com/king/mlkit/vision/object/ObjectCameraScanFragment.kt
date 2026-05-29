package com.king.mlkit.vision.object

import com.google.mlkit.vision.objects.DetectedObject
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.object.analyze.ObjectDetectionAnalyzer

abstract class ObjectCameraScanFragment : BaseCameraScanFragment<List<DetectedObject>>() {
    override fun createAnalyzer(): Analyzer<List<DetectedObject>>? = ObjectDetectionAnalyzer()
}
