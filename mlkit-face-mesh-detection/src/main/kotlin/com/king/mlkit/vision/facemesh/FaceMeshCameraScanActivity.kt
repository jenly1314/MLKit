package com.king.mlkit.vision.facemesh

import com.google.mlkit.vision.facemesh.FaceMesh
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.facemesh.analyze.FaceMeshDetectionAnalyzer

/**
 * 人脸网格扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class FaceMeshCameraScanActivity : BaseCameraScanActivity<List<FaceMesh>>() {
    override fun createAnalyzer(): Analyzer<List<FaceMesh>>? = FaceMeshDetectionAnalyzer()
}
