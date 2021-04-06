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
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.Barcode
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.base.util.ToastUtils
import com.king.base.util.UriUtils
import com.king.mlkit.vision.app.`object`.MultipleObjectDetectionActivity
import com.king.mlkit.vision.app.`object`.ObjectDetectionActivity
import com.king.mlkit.vision.app.barcode.BarcodeScanningActivity
import com.king.mlkit.vision.app.barcode.QRCodeScanningActivity
import com.king.mlkit.vision.app.face.FaceDetectionActivity
import com.king.mlkit.vision.app.face.MultipleFaceDetectionActivity
import com.king.mlkit.vision.app.image.ImageLabelingActivity
import com.king.mlkit.vision.app.pose.AccuratePoseDetectionActivity
import com.king.mlkit.vision.app.pose.PoseDetectionActivity
import com.king.mlkit.vision.app.segmentation.SelfieSegmentationActivity
import com.king.mlkit.vision.app.text.TextRecognitionActivity
import com.king.mlkit.vision.barcode.BarcodeDecoder
import com.king.mlkit.vision.camera.analyze.Analyzer.OnAnalyzeListener
import com.king.mlkit.vision.camera.util.LogUtils
import com.king.mlkit.vision.camera.util.PermissionUtils
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    companion object{

        const val REQUEST_CODE_PHOTO = 1
        const val REQUEST_CODE_REQUEST_EXTERNAL_STORAGE = 2
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

    private fun processPhoto(data: Intent?){
        data?.let {
            try{
                val src = BitmapFactory.decodeFile(UriUtils.getPath(getContext(),data.data))
                BarcodeDecoder.process(src, object : OnAnalyzeListener<List<Barcode>?> {
                    override fun onSuccess(result: List<Barcode>) {
                        if(result?.isNotEmpty()){
                            val buffer = StringBuilder()
                            val bitmap = src.drawRect { canvas, paint ->
                                for ((index,data) in result.withIndex()) {
                                    buffer.append("[$index] ").append(data.displayValue).append("\n")
                                    canvas.drawRect(data.boundingBox,paint)
                                }
                            }

                            val config = AppDialogConfig(getContext(),R.layout.barcode_result_dialog)
                            config.setContent(buffer)
                                    .setHideCancel(true)
                                    .setOnClickOk {
                                AppDialog.INSTANCE.dismissDialog()
                            }
                            val imageView = config.getView<ImageView>(R.id.ivDialogContent)
                            imageView.setImageBitmap(bitmap)
                            AppDialog.INSTANCE.showDialog(config)
                        }else{
                            LogUtils.d("result is null")
                            ToastUtils.showToast(getContext(),"result is null")
                        }
                    }
                    override fun onFailure() {
                        LogUtils.d("onFailure")
                        ToastUtils.showToast(getContext(),"onFailure")
                    }
                })
            }catch (e: Exception){

            }

        }
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    private fun pickPhotoClicked(){
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
            R.id.btn0 -> startActivity(QRCodeScanningActivity::class.java)
            R.id.btn1 -> startActivity(BarcodeScanningActivity::class.java)
            R.id.btn2 -> pickPhotoClicked()
            R.id.btn3 -> startActivity(FaceDetectionActivity::class.java)
            R.id.btn4 -> startActivity(MultipleFaceDetectionActivity::class.java)
            R.id.btn5 -> startActivity(ImageLabelingActivity::class.java)
            R.id.btn6 -> startActivity(ObjectDetectionActivity::class.java)
            R.id.btn7 -> startActivity(MultipleObjectDetectionActivity::class.java)
            R.id.btn8 -> startActivity(PoseDetectionActivity::class.java)
            R.id.btn9 -> startActivity(AccuratePoseDetectionActivity::class.java)
            R.id.btn10 -> startActivity(SelfieSegmentationActivity::class.java)
            R.id.btn11 -> startActivity(TextRecognitionActivity::class.java)
        }
    }


}