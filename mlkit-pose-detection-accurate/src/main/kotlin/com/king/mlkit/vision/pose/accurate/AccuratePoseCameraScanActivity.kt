package com.king.mlkit.vision.pose.accurate

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.accurate.analyze.AccuratePoseDetectionAnalyzer

/**
 * 准确姿势检测（精确）扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class AccuratePoseCameraScanActivity : BaseCameraScanActivity<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = AccuratePoseDetectionAnalyzer()
}
