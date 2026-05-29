package com.king.mlkit.vision.barcode

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.io.IOException
import java.util.regex.Pattern

class BarcodeDecoder private constructor() {
    companion object {
        @JvmStatic
        @Throws(IOException::class)
        fun fromFilePath(context: Context, uri: Uri): InputImage = InputImage.fromFilePath(context, uri)

        @JvmStatic
        fun fromBitmap(bitmap: Bitmap): InputImage = fromBitmap(bitmap, 0)

        @JvmStatic
        fun fromBitmap(bitmap: Bitmap, rotation: Int): InputImage = InputImage.fromBitmap(bitmap, rotation)

        @JvmStatic
        fun process(bitmap: Bitmap): Task<List<Barcode>> = process(fromBitmap(bitmap), Barcode.FORMAT_ALL_FORMATS)

        @JvmStatic
        fun process(bitmap: Bitmap, options: BarcodeScannerOptions): Task<List<Barcode>> = process(fromBitmap(bitmap), options)

        @JvmStatic
        fun process(bitmap: Bitmap, @Barcode.BarcodeFormat format: Int, @Barcode.BarcodeFormat vararg formats: Int): Task<List<Barcode>> =
            process(fromBitmap(bitmap), format, *formats)

        @JvmStatic
        fun process(inputImage: InputImage): Task<List<Barcode>> = process(inputImage, Barcode.FORMAT_ALL_FORMATS)

        @JvmStatic
        fun process(inputImage: InputImage, @Barcode.BarcodeFormat format: Int, @Barcode.BarcodeFormat vararg formats: Int): Task<List<Barcode>> =
            process(inputImage, BarcodeScannerOptions.Builder().setBarcodeFormats(format, *formats).build())

        @JvmStatic
        fun process(inputImage: InputImage, options: BarcodeScannerOptions): Task<List<Barcode>> =
            BarcodeScanning.getClient(options).process(inputImage)

        @JvmStatic
        fun getBarcode(barcodeList: List<Barcode>?, regex: String?, allowDefault: Boolean): Barcode? {
            if (barcodeList != null && barcodeList.isNotEmpty()) {
                if (!TextUtils.isEmpty(regex)) {
                    for (barcode in barcodeList) {
                        val rawValue = barcode.rawValue ?: continue
                        if (Pattern.matches(regex!!, rawValue)) {
                            return barcode
                        }
                    }
                }
                if (allowDefault) {
                    return barcodeList[0]
                }
            }
            return null
        }
    }
}
