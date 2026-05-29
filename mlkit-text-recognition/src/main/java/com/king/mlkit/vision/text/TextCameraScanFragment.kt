package com.king.mlkit.vision.text

import com.google.mlkit.vision.text.Text
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.text.analyze.TextRecognitionAnalyzer

abstract class TextCameraScanFragment : BaseCameraScanFragment<Text>() {
    override fun createAnalyzer(): Analyzer<Text>? = TextRecognitionAnalyzer()
}
