package com.king.mlkit.vision.pose.accurate.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

class AccuratePoseDetectionAnalyzer(options: PoseDetectorOptionsBase? = null) : CommonAnalyzer<Pose>() {

    private val detector: PoseDetector = if (options != null) {
        PoseDetection.getClient(options)
    } else {
        PoseDetection.getClient(
            AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<Pose> = detector.process(inputImage)
}
