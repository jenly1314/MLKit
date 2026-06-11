package com.king.mlkit.vision.segmentation.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.segmentation.Segmentation
import com.google.mlkit.vision.segmentation.SegmentationMask
import com.google.mlkit.vision.segmentation.Segmenter
import com.google.mlkit.vision.segmentation.selfie.SelfieSegmenterOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 自拍图像分割分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class SegmentationAnalyzer(options: SelfieSegmenterOptions? = null) : CommonAnalyzer<SegmentationMask>() {

    private val detector: Segmenter = if (options != null) {
        Segmentation.getClient(options)
    } else {
        Segmentation.getClient(
            SelfieSegmenterOptions.Builder()
                .setDetectorMode(SelfieSegmenterOptions.STREAM_MODE)
                .enableRawSizeMask()
                .build()
        )
    }

    override fun detectInImage(inputImage: InputImage): Task<SegmentationMask> = detector.process(inputImage)
}
