package com.king.mlkit.vision.`object`.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 对象检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class ObjectDetectionAnalyzer(options: ObjectDetectorOptionsBase? = null) : CommonAnalyzer<List<DetectedObject>>() {

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
