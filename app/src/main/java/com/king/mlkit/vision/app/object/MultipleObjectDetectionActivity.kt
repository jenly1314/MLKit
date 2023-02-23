package com.king.mlkit.vision.app.`object`

import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.king.mlkit.vision.camera.analyze.Analyzer
import com.king.mlkit.vision.`object`.analyze.ObjectDetectionAnalyzer

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class MultipleObjectDetectionActivity : ObjectDetectionActivity() {

    override fun createAnalyzer(): Analyzer<MutableList<DetectedObject>>? {
        return ObjectDetectionAnalyzer(
            ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableMultipleObjects()
                .enableClassification()  // Optional
                .build()
        )
    }
}