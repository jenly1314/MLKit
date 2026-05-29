package com.king.mlkit.vision.face.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

class FaceDetectionAnalyzer(options: FaceDetectorOptions? = null) : CommonAnalyzer<List<Face>>() {

    private val detector: FaceDetector = if (options != null) {
        FaceDetection.getClient(options)
    } else {
        FaceDetection.getClient(
            FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<List<Face>> = detector.process(inputImage)
}
