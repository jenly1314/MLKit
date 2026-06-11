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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.google.mlkit.vision.barcode.common.Barcode
import com.king.app.dialog.AppDialog
import com.king.app.dialog.appDialogConfig
import com.king.camera.scan.CameraScan
import com.king.logx.LogX
import com.king.mlkit.vision.app.barcode.BarcodeScanningActivity
import com.king.mlkit.vision.app.barcode.MultipleQRCodeScanningActivity
import com.king.mlkit.vision.app.barcode.QRCodeScanningActivity
import com.king.mlkit.vision.app.ext.drawRect
import com.king.mlkit.vision.app.ext.getBitmap
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
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
class MainActivity : AppCompatActivity() {

    private var isQrCode = false

    private var toast: Toast? = null

    private val startActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            processScanResult(result.data)
        }
    }

    private val pickPhotoLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.also {
            processPickPhotoResult(it)
        }
    }

    private fun showToast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 扫描结果
     */
    private fun processScanResult(data: Intent?) {
        val text = CameraScan.parseScanResult(data)
        showToast("$text")
    }

    /**
     * 处理选择图片后 - 从图片中获取条码结果
     */
    private fun processPickPhotoResult(data: Uri?) {
        data?.let {
            try {
                BarcodeDecoder.process(
                    BarcodeDecoder.fromFilePath(this@MainActivity, it),
                    // 如果指定具体的识别条码类型，速度会更快
                    if (isQrCode) Barcode.FORMAT_QR_CODE else Barcode.FORMAT_ALL_FORMATS
                ).addOnSuccessListener(this) { result ->
                    if (result.isNotEmpty()) {
                        val buffer = StringBuilder()

                        // 成功；在图片上框出结果
                        val bitmap = getBitmap(it).drawRect { canvas, paint ->
                            for ((index, barcode) in result.withIndex()) {
                                buffer.append("[$index] ").append(barcode.displayValue)
                                    .append("\n")
                                barcode.boundingBox?.let { box ->
                                    canvas.drawRect(box, paint)
                                }
                            }
                        }
                        val config = appDialogConfig(R.layout.barcode_result_dialog) {
                            content = buffer
                            hideCancel = true
                            setOnClickConfirm {
                                AppDialog.dismissDialog()
                            }
                            viewHolder.getView<ImageView>(R.id.ivDialogContent).setImageBitmap(
                                bitmap
                            )
                        }
                        AppDialog.showDialog(config)
                    } else {
                        // 没有结果
                        LogX.d("result is empty")
                        showToast("result is empty")
                    }
                }.addOnFailureListener(this) { e ->
                    // 失败
                    LogX.w(e)
                }
            } catch (e: Exception) {
                LogX.w(e)
            }
        }
    }

    private fun startActivity(cls: Class<*>) {
        val optionsCompat = ActivityOptionsCompat.makeCustomAnimation(
            this, android.R.anim.fade_in, android.R.anim.fade_out
        )
        startActivityLauncher.launch(Intent(this, cls), optionsCompat)
    }

    private fun pickPhoto(isQRCode: Boolean) {
        this.isQrCode = isQRCode
        pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.btnQRCodeScanning -> startActivity(QRCodeScanningActivity::class.java)
            R.id.btnMultipleQRCodeScanning -> startActivity(MultipleQRCodeScanningActivity::class.java)
            R.id.btnBarcodeScanning -> startActivity(BarcodeScanningActivity::class.java)
            R.id.btnQRCodeRecognitionFromImage -> pickPhoto(true)
            R.id.btnBarcodeRecognitionFromImage -> pickPhoto(false)
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

}
