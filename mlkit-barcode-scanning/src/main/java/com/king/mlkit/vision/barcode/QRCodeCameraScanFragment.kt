package com.king.mlkit.vision.barcode

import com.google.mlkit.vision.barcode.common.Barcode
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.barcode.analyze.BarcodeScanningAnalyzer

abstract class QRCodeCameraScanFragment : BarcodeCameraScanFragment() {
    override fun createAnalyzer(): Analyzer<List<Barcode>>? = BarcodeScanningAnalyzer(Barcode.FORMAT_QR_CODE)
}
