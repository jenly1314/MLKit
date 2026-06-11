package com.king.mlkit.vision.text

import com.google.mlkit.vision.text.Text
import com.king.camera.scan.BaseCameraScanFragment
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.text.analyze.TextRecognitionAnalyzer

/**
 * 文本识别扫描Fragment
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class TextCameraScanFragment : BaseCameraScanFragment<Text>() {
    override fun createAnalyzer(): Analyzer<Text>? = TextRecognitionAnalyzer()
}
