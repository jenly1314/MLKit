package com.king.mlkit.vision.app.segmentation

import com.google.mlkit.vision.segmentation.SegmentationMask
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.mlkit.vision.camera.AnalyzeResult
import com.king.mlkit.vision.segmentation.SegmentationCameraScanActivity

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class SelfieSegmentationActivity : SegmentationCameraScanActivity() {

    override fun onScanResultCallback(result: AnalyzeResult<SegmentationMask>) {
        cameraScan.setAnalyzeImage(false)

        val mask = result.result.buffer
        val maskWidth = result.result.width
        val maskHeight = result.result.height

        val config = AppDialogConfig(this)
        config.setContent("${mask.float}")
            .setOnClickOk {
                AppDialog.INSTANCE.dismissDialog()
                cameraScan.setAnalyzeImage(true)
            }.setOnClickCancel {
                AppDialog.INSTANCE.dismissDialog()
                finish()
            }
        AppDialog.INSTANCE.showDialog(config)
    }
}