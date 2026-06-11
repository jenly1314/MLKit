package com.king.mlkit.vision.barcode

import com.google.mlkit.vision.barcode.common.Barcode
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.barcode.analyze.BarcodeScanningAnalyzer

/**
 * 二维码扫描Activity
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class QRCodeCameraScanActivity : BarcodeCameraScanActivity() {
    override fun createAnalyzer(): Analyzer<List<Barcode>>? = BarcodeScanningAnalyzer(Barcode.FORMAT_QR_CODE)
}
