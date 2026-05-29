package com.king.mlkit.vision.text.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

open class TextRecognitionAnalyzer(options: TextRecognizerOptionsInterface? = null) : CommonAnalyzer<Text>() {

    private val detector: TextRecognizer = if (options != null) {
        TextRecognition.getClient(options)
    } else {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    }

    override fun detectInImage(inputImage: InputImage): Task<Text> = detector.process(inputImage)
}
