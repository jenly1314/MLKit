package com.king.mlkit.vision.face

import com.google.mlkit.vision.face.Face
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.face.analyze.FaceDetectionAnalyzer

abstract class FaceCameraScanFragment : BaseCameraScanFragment<List<Face>>() {
    override fun createAnalyzer(): Analyzer<List<Face>>? = FaceDetectionAnalyzer()
}
