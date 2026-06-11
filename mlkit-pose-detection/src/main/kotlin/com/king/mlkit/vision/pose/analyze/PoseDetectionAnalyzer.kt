package com.king.mlkit.vision.pose.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 姿势检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class PoseDetectionAnalyzer(options: PoseDetectorOptionsBase? = null) : CommonAnalyzer<Pose>() {

    private val detector: PoseDetector = if (options != null) {
        PoseDetection.getClient(options)
    } else {
        PoseDetection.getClient(
            PoseDetectorOptions.Builder()
                .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<Pose> = detector.process(inputImage)
}
