package com.king.mlkit.vision.pose

import com.google.mlkit.vision.pose.Pose
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.pose.analyze.PoseDetectionAnalyzer

/**
 * 姿势检测扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class PoseCameraScanActivity : BaseCameraScanActivity<Pose>() {
    override fun createAnalyzer(): Analyzer<Pose>? = PoseDetectionAnalyzer()
}
