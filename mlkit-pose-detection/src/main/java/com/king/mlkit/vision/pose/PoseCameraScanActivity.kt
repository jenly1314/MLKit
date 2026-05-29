package com.king.mlkit.vision.pose

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.analyze.PoseDetectionAnalyzer

abstract class PoseCameraScanActivity : BaseCameraScanActivity<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = PoseDetectionAnalyzer()
}
