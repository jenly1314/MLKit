package com.king.mlkit.vision.facemesh.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.facemesh.FaceMesh
import com.google.mlkit.vision.facemesh.FaceMeshDetection
import com.google.mlkit.vision.facemesh.FaceMeshDetector
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 人脸网格检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class FaceMeshDetectionAnalyzer(options: FaceMeshDetectorOptions? = null) : CommonAnalyzer<List<FaceMesh>>() {

    private val detector: FaceMeshDetector = if (options != null) {
        FaceMeshDetection.getClient(options)
    } else {
        FaceMeshDetection.getClient()
    }

    override fun detectInImage(inputImage: InputImage): Task<List<FaceMesh>> = detector.process(inputImage)
}
