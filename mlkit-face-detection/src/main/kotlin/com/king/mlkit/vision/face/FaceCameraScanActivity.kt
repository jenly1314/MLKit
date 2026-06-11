package com.king.mlkit.vision.face

import com.google.mlkit.vision.face.Face
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.face.analyze.FaceDetectionAnalyzer

/**
 * 人脸检测扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class FaceCameraScanActivity : BaseCameraScanActivity<List<Face>>() {
    override fun createAnalyzer(): Analyzer<List<Face>>? = FaceDetectionAnalyzer()
}
