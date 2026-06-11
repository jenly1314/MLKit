package com.king.mlkit.vision.barcode.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

/**
 * 条码扫描分析器
 *
 * 根据需要，可以通过构造函数指定要检测的条形码格式，以优化性能和准确性。
 *
 * 支持检测识别的条形码格式主要有：
 *   - 线性格式：Codabar, Code 39, Code 93, Code 128, EAN-8, EAN-13, ITF, UPC-A, UPC-E
 *   - 2D格式：Aztec, Data Matrix, PDF417, QR Code
 *
 * 具体条码格式定义见：[Barcode.BarcodeFormat]
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
open class BarcodeScanningAnalyzer : CommonAnalyzer<List<Barcode>> {

    private val detector: BarcodeScanner

    constructor() {
        detector = BarcodeScanning.getClient()
    }

    constructor(@Barcode.BarcodeFormat barcodeFormat: Int, @Barcode.BarcodeFormat vararg barcodeFormats: Int) : this(
        BarcodeScannerOptions.Builder().setBarcodeFormats(barcodeFormat, *barcodeFormats).build()
    )

    constructor(options: BarcodeScannerOptions?) {
        detector = if (options != null) BarcodeScanning.getClient(options) else BarcodeScanning.getClient()
    }

    override fun detectInImage(inputImage: InputImage): Task<List<Barcode>> = detector.process(inputImage)
}
