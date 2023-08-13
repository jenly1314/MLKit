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
package com.king.mlkit.vision.app.barcode

import android.content.Intent
import android.graphics.Point
import android.widget.ImageView
import com.google.mlkit.vision.barcode.common.Barcode
import com.king.camera.scan.AnalyzeResult
import com.king.camera.scan.CameraScan
import com.king.camera.scan.util.PointUtils
import com.king.mlkit.vision.app.R
import com.king.mlkit.vision.barcode.QRCodeCameraScanActivity

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class QRCodeScanningActivity : QRCodeCameraScanActivity() {

    private lateinit var ivResult: ImageView

    override fun initUI() {
        super.initUI()

        ivResult = findViewById(R.id.ivResult)
    }

    override fun initCameraScan(cameraScan: CameraScan<MutableList<Barcode>>) {
        super.initCameraScan(cameraScan)
        cameraScan.setPlayBeep(true)
            .setVibrate(true)
            .bindFlashlightView(ivFlashlight)
    }

    override fun getLayoutId(): Int {
        return R.layout.qrcode_scan_activity
    }

    override fun onBackPressed() {
        if (viewfinderView.isShowPoints) {//如果是结果点显示时，用户点击了返回键，则认为是取消选择当前结果，重新开始扫码
            ivResult.setImageResource(0)
            viewfinderView.showScanner()
            cameraScan.setAnalyzeImage(true)
            return
        }
        super.onBackPressed()
    }

    override fun onScanResultCallback(result: AnalyzeResult<MutableList<Barcode>>) {

        cameraScan.setAnalyzeImage(false)
        val results = result.result

        //取预览当前帧图片并显示，为结果点提供参照
        ivResult.setImageBitmap(previewView.bitmap)
        val points = ArrayList<Point>()
        val frameMetadata = result.frameMetadata
        var width = frameMetadata.width
        var height = frameMetadata.height
        if(frameMetadata.rotation == 90 || frameMetadata.rotation == 270) {
            width = frameMetadata.height
            height = frameMetadata.width
        }
        for (barcode in results) {
            barcode.boundingBox?.let { box ->
                //将实际的结果中心点坐标转换成界面预览的坐标
                val point = PointUtils.transform(
                    box.centerX(),
                    box.centerY(),
                    width,
                    height,
                    viewfinderView.width,
                    viewfinderView.height
                )
                points.add(point)
            }
        }
        //设置Item点击监听
        viewfinderView.setOnItemClickListener {
            //显示点击Item将所在位置扫码识别的结果返回
            val intent = Intent()
            intent.putExtra(CameraScan.SCAN_RESULT, results[it].displayValue)
            setResult(RESULT_OK, intent)
            finish()

            /*
                显示结果后，如果需要继续扫码，则可以继续分析图像
             */
//            ivResult.setImageResource(0)
//            viewfinderView.showScanner()
//            cameraScan.setAnalyzeImage(true)
        }
        //显示结果点信息
        viewfinderView.showResultPoints(points)

        if (results.size == 1) {//只有一个结果直接返回
            val intent = Intent()
            intent.putExtra(CameraScan.SCAN_RESULT, results[0].displayValue)
            setResult(RESULT_OK, intent)
            finish()
        }

    }


}