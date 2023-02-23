package com.king.mlkit.vision.app.face

import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.king.mlkit.vision.camera.analyze.Analyzer
import com.king.mlkit.vision.face.analyze.FaceDetectionAnalyzer

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class MultipleFaceDetectionActivity : FaceDetectionActivity() {

    override fun createAnalyzer(): Analyzer<MutableList<Face>>? {
        return FaceDetectionAnalyzer(
            FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build()
        )
    }
}