/*
 * Copyright (C) Jenly, MLKit Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.king.mlkit.vision.app

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.common.Barcode
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.camera.scan.CameraScan
import com.king.camera.scan.util.LogUtils
import com.king.camera.scan.util.PermissionUtils
import com.king.mlkit.vision.app.barcode.BarcodeScanningActivity
import com.king.mlkit.vision.app.barcode.MultipleQRCodeScanningActivity
import com.king.mlkit.vision.app.barcode.QRCodeScanningActivity
import com.king.mlkit.vision.app.face.FaceDetectionActivity
import com.king.mlkit.vision.app.face.FaceMeshDetectionActivity
import com.king.mlkit.vision.app.face.MultipleFaceDetectionActivity
import com.king.mlkit.vision.app.image.ImageLabelingActivity
import com.king.mlkit.vision.app.`object`.MultipleObjectDetectionActivity
import com.king.mlkit.vision.app.`object`.ObjectDetectionActivity
import com.king.mlkit.vision.app.pose.AccuratePoseDetectionActivity
import com.king.mlkit.vision.app.pose.PoseDetectionActivity
import com.king.mlkit.vision.app.segmentation.SelfieSegmentationActivity
import com.king.mlkit.vision.app.text.TextRecognitionActivity
import com.king.mlkit.vision.barcode.BarcodeDecoder

/**
 * 演示示例
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class MainActivity : AppCompatActivity() {

    private var isQrCode = false

    private var toast: Toast? = null

    private fun showToast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PHOTO -> processPhoto(data?.data)
                REQUEST_CODE_SCAN_CODE -> processScanResult(data)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_REQUEST_EXTERNAL_STORAGE && PermissionUtils.requestPermissionsResult(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                permissions,
                grantResults
            )
        ) {
            startPickPhoto()
        }
    }

    private fun getContext() = this

    /**
     * 扫描结果
     */
    private fun processScanResult(data: Intent?) {
        val text = CameraScan.parseScanResult(data)
        showToast("$text")
    }

    /**
     * 处理图片 - 从图片中获取条码结果
     */
    private fun processPhoto(data: Uri?) {
        data?.let {
            try {
                BarcodeDecoder.process(
                    BarcodeDecoder.fromFilePath(getContext(), it),
                    // 如果指定具体的识别条码类型，速度会更快
                    if (isQrCode) Barcode.FORMAT_QR_CODE else Barcode.FORMAT_ALL_FORMATS
                ).addOnSuccessListener(this) { result ->
                    if (result.isNotEmpty()) {
                        val buffer = StringBuilder()
                        // 成功；在图片上框出结果
                        val bitmap = it.getBitmap().drawRect { canvas, paint ->
                            for ((index, barcode) in result.withIndex()) {
                                buffer.append("[$index] ").append(barcode.displayValue)
                                    .append("\n")
                                barcode.boundingBox?.let { box ->
                                    canvas.drawRect(box, paint)
                                }
                            }
                        }
                        val config = AppDialogConfig(getContext(), R.layout.barcode_result_dialog)
                        config.setContent(buffer)
                            .setHideCancel(true)
                            .setOnClickConfirm {
                                AppDialog.INSTANCE.dismissDialog()
                            }
                        val imageView = config.getView<ImageView>(R.id.ivDialogContent)
                        imageView.setImageBitmap(bitmap)
                        AppDialog.INSTANCE.showDialog(config)
                    } else {
                        // 没有结果
                        LogUtils.d("result is empty")
                        showToast("result is empty")
                    }
                }.addOnFailureListener(this) { e ->
                    // 失败
                    LogUtils.w(e)
                }
            } catch (e: Exception) {
                LogUtils.w(e)
            }
        }
    }

    /**
     * 根据Uri获取对应的图片
     */
    private fun Uri.getBitmap(): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, this)
        }
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    private fun pickPhotoClicked(isQRCode: Boolean) {
        this.isQrCode = isQRCode
        if (PermissionUtils.checkPermission(
                getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            startPickPhoto()
        } else {
            PermissionUtils.requestPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                REQUEST_CODE_REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    /**
     * 选择照片 - 条形码/二维码 图片识别
     */
    private fun startPickPhoto() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(pickIntent, REQUEST_CODE_PHOTO)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnQRCodeScanning -> startActivityForResult(
                Intent(this, QRCodeScanningActivity::class.java),
                REQUEST_CODE_SCAN_CODE
            )

            R.id.btnMultipleQRCodeScanning -> startActivity(MultipleQRCodeScanningActivity::class.java)
            R.id.btnBarcodeScanning -> startActivity(BarcodeScanningActivity::class.java)
            R.id.btnQRCodeRecognitionFromImage -> pickPhotoClicked(true)
            R.id.btnBarcodeRecognitionFromImage -> pickPhotoClicked(false)
            R.id.btnFaceDetectionAndClassification -> startActivity(FaceDetectionActivity::class.java)
            R.id.btnMultipleFaceDetection -> startActivity(MultipleFaceDetectionActivity::class.java)
            R.id.btnFaceMeshDetection -> startActivity(FaceMeshDetectionActivity::class.java)
            R.id.btnImageLabeling -> startActivity(ImageLabelingActivity::class.java)
            R.id.btnObjectDetectionAndTracking -> startActivity(ObjectDetectionActivity::class.java)
            R.id.btnMultipleObjectDetection -> startActivity(MultipleObjectDetectionActivity::class.java)
            R.id.btnPoseDetection -> startActivity(PoseDetectionActivity::class.java)
            R.id.btnPoseDetectionAccurate -> startActivity(AccuratePoseDetectionActivity::class.java)
            R.id.btnSelfieSegmentation -> startActivity(SelfieSegmentationActivity::class.java)
            R.id.btnTextRecognition -> startActivity(TextRecognitionActivity::class.java)
        }
    }

    companion object {

        const val REQUEST_CODE_PHOTO = 1
        const val REQUEST_CODE_REQUEST_EXTERNAL_STORAGE = 2
        const val REQUEST_CODE_SCAN_CODE = 3
    }

}