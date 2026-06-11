package com.king.mlkit.vision.pose.accurate

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.accurate.analyze.AccuratePoseDetectionAnalyzer

/**
 * 姿势检测（精确）扫描Fragment
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class AccuratePoseCameraScanFragment : BaseCameraScanFragment<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = AccuratePoseDetectionAnalyzer()
}
