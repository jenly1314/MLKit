package com.king.mlkit.vision.face.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 人脸检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class FaceDetectionAnalyzer(options: FaceDetectorOptions? = null) : CommonAnalyzer<List<Face>>() {

    private val detector: FaceDetector = if (options != null) {
        FaceDetection.getClient(options)
    } else {
        FaceDetection.getClient(
            FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<List<Face>> = detector.process(inputImage)
}
