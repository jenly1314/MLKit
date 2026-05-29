package com.king.mlkit.vision.face

import com.google.mlkit.vision.face.Face
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.face.analyze.FaceDetectionAnalyzer

abstract class FaceCameraScanActivity : BaseCameraScanActivity<List<Face>>() {
    override fun createAnalyzer(): Analyzer<List<Face>>? = FaceDetectionAnalyzer()
}
