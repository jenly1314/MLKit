package com.king.mlkit.vision.image.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabelerOptionsBase
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 图像标签分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class ImageLabelingAnalyzer(options: ImageLabelerOptionsBase? = null) : CommonAnalyzer<List<ImageLabel>>() {

    private val detector: ImageLabeler = if (options != null) {
        ImageLabeling.getClient(options)
    } else {
        ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
    }

    override fun detectInImage(inputImage: InputImage): Task<List<ImageLabel>> = detector.process(inputImage)
}
