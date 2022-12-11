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
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.common.Barcode
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.mlkit.vision.app.`object`.MultipleObjectDetectionActivity
import com.king.mlkit.vision.app.`object`.ObjectDetectionActivity
import com.king.mlkit.vision.app.barcode.BarcodeScanningActivity
import com.king.mlkit.vision.app.barcode.MultipleQRCodeScanningActivity
import com.king.mlkit.vision.app.barcode.QRCodeScanningActivity
import com.king.mlkit.vision.app.face.FaceDetectionActivity
import com.king.mlkit.vision.app.face.FaceMeshDetectionActivity
import com.king.mlkit.vision.app.face.MultipleFaceDetectionActivity
import com.king.mlkit.vision.app.image.ImageLabelingActivity
import com.king.mlkit.vision.app.pose.AccuratePoseDetectionActivity
import com.king.mlkit.vision.app.pose.PoseDetectionActivity
import com.king.mlkit.vision.app.segmentation.SelfieSegmentationActivity
import com.king.mlkit.vision.app.text.TextRecognitionActivity
import com.king.mlkit.vision.barcode.BarcodeDecoder
import com.king.mlkit.vision.camera.CameraScan
import com.king.mlkit.vision.camera.analyze.Analyzer.OnAnalyzeListener
import com.king.mlkit.vision.camera.util.LogUtils
import com.king.mlkit.vision.camera.util.PermissionUtils
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    var isQRCode = false

    companion object{

        const val REQUEST_CODE_PHOTO = 1
        const val REQUEST_CODE_REQUEST_EXTERNAL_STORAGE = 2

        const val REQUEST_CODE_SCAN_CODE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQUEST_CODE_PHOTO -> processPhoto(data)
                REQUEST_CODE_SCAN_CODE -> processScanResult(data)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_REQUEST_EXTERNAL_STORAGE && PermissionUtils.requestPermissionsResult(Manifest.permission.READ_EXTERNAL_STORAGE,permissions,grantResults)){
            startPickPhoto()
        }
    }

    fun getContext() = this

    private fun processScanResult(data: Intent?){
        val text = CameraScan.parseScanResult(data)
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    private fun processPhoto(data: Intent?){
        data?.let {
            try{
                val src = MediaStore.Images.Media.getBitmap(contentResolver,it.data)
                BarcodeDecoder.process(src, object : OnAnalyzeListener<List<Barcode>?> {
                    override fun onSuccess(result: List<Barcode>) {
                        if(result.isNotEmpty()){
                            val buffer = StringBuilder()
                            val bitmap = src.drawRect { canvas, paint ->
                                for ((index, barcode) in result.withIndex()) {
                                    buffer.append("[$index] ").append(barcode.displayValue).append("\n")
                                    barcode.boundingBox?.let{ box ->
                                        canvas.drawRect(box, paint)
                                    }

                                }
                            }

                            val config = AppDialogConfig(getContext(),R.layout.barcode_result_dialog)
                            config.setContent(buffer)
                                    .setHideCancel(true)
                                    .setOnClickConfirm {
                                AppDialog.INSTANCE.dismissDialog()
                            }
                            val imageView = config.getView<ImageView>(R.id.ivDialogContent)
                            imageView.setImageBitmap(bitmap)
                            AppDialog.INSTANCE.showDialog(config)
                        }else{
                            LogUtils.d("result is null")
                            Toast.makeText(getContext(),"result is null", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure() {
                        LogUtils.d("onFailure")
                        Toast.makeText(getContext(),"onFailure", Toast.LENGTH_SHORT).show()
                    }
                //如果指定具体的识别条码类型，速度会更快
                },if(isQRCode) Barcode.FORMAT_QR_CODE else Barcode.FORMAT_ALL_FORMATS)
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(getContext(),e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    private fun pickPhotoClicked(isQRCode: Boolean){
        this.isQRCode = isQRCode
        if(PermissionUtils.checkPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)){
            startPickPhoto()
        }else{
            PermissionUtils.requestPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE,REQUEST_CODE_REQUEST_EXTERNAL_STORAGE)
        }
    }

    private fun startPickPhoto(){
        val pickIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(pickIntent, REQUEST_CODE_PHOTO)
    }


    fun onClick(v: View){
        when (v.id){
            R.id.btnQRCodeScanning -> startActivityForResult(Intent(this,QRCodeScanningActivity::class.java),REQUEST_CODE_SCAN_CODE)
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


}