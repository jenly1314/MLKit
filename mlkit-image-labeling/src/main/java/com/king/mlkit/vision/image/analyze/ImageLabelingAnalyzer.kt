package com.king.mlkit.vision.image.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabelerOptionsBase
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

open class ImageLabelingAnalyzer(options: ImageLabelerOptionsBase? = null) : CommonAnalyzer<List<ImageLabel>>() {

    private val detector: ImageLabeler = if (options != null) {
        ImageLabeling.getClient(options)
    } else {
        ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
    }

    override fun detectInImage(inputImage: InputImage): Task<List<ImageLabel>> = detector.process(inputImage)
}
