package com.king.mlkit.vision.pose

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.analyze.PoseDetectionAnalyzer

abstract class PoseCameraScanFragment : BaseCameraScanFragment<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = PoseDetectionAnalyzer()
}
