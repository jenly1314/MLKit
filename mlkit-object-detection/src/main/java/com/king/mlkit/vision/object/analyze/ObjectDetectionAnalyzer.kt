package com.king.mlkit.vision.object.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

class ObjectDetectionAnalyzer(options: ObjectDetectorOptionsBase? = null) : CommonAnalyzer<List<DetectedObject>>() {

    private val detector: ObjectDetector = if (options != null) {
        ObjectDetection.getClient(options)
    } else {
        ObjectDetection.getClient(
            ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                .enableClassification()
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<List<DetectedObject>> = detector.process(inputImage)
}
