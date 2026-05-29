package com.king.mlkit.vision.pose.accurate

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.accurate.analyze.AccuratePoseDetectionAnalyzer

abstract class AccuratePoseCameraScanFragment : BaseCameraScanFragment<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = AccuratePoseDetectionAnalyzer()
}
