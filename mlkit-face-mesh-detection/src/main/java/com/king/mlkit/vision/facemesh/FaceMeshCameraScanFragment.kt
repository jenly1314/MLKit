package com.king.mlkit.vision.facemesh

import com.google.mlkit.vision.facemesh.FaceMesh
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.facemesh.analyze.FaceMeshDetectionAnalyzer

abstract class FaceMeshCameraScanFragment : BaseCameraScanFragment<List<FaceMesh>>() {
    override fun createAnalyzer(): Analyzer<List<FaceMesh>>? = FaceMeshDetectionAnalyzer()
}
