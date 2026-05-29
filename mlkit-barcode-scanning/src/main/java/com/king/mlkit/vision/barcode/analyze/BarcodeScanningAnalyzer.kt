package com.king.mlkit.vision.barcode.analyze

import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.king.mlkit.vision.common.analyze.CommonAnalyzer

class BarcodeScanningAnalyzer : CommonAnalyzer<List<Barcode>> {

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
