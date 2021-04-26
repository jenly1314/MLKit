package com.king.mlkit.vision.app.segmentation

import android.graphics.*
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.google.mlkit.vision.segmentation.SegmentationMask
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.mlkit.vision.app.R
import com.king.mlkit.vision.camera.AnalyzeResult
import com.king.mlkit.vision.segmentation.SegmentationCameraScanActivity

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class SelfieSegmentationActivity : SegmentationCameraScanActivity() {

    override fun onScanResultCallback(result: AnalyzeResult<SegmentationMask>) {
        cameraScan.setAnalyzeImage(false)

        val config = AppDialogConfig(this, R.layout.result_dialog)
        config.setOnClickOk {
            AppDialog.INSTANCE.dismissDialog()
            cameraScan.setAnalyzeImage(true)
        }.setOnClickCancel {
            AppDialog.INSTANCE.dismissDialog()
            finish()
        }
        val imageView = config.getView<ImageView>(R.id.ivDialogContent)
        imageView.setImageBitmap(processBitmap(result))
        AppDialog.INSTANCE.showDialog(config,false)
    }

    private fun processBitmap(result: AnalyzeResult<SegmentationMask>): Bitmap{
        val bitmap = result.bitmap
        val mask = result.result
        val maskWidth = mask.width
        val maskHeight = mask.height

        val isRawSizeMaskEnabled = maskWidth != bitmap.width || maskHeight != bitmap.height
        val scaleX = bitmap.width * 1f / maskWidth
        val scaleY = bitmap.height * 1f / maskHeight


        val resultBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val maskBitmap = Bitmap.createBitmap(maskColorsFromByteBuffer(mask), maskWidth, maskHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(resultBitmap)
        val paint = Paint()
        //绘制原始图片
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        paint.strokeWidth = 6f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        val matrix = Matrix()
        if (isRawSizeMaskEnabled) {
            matrix.preScale(scaleX, scaleY)
        }
        //绘制蒙版图层
        canvas.drawBitmap(maskBitmap, matrix, null)
        bitmap.recycle()

        canvas.save()
        canvas.restore()

        return resultBitmap
    }

    @ColorInt
    private fun maskColorsFromByteBuffer(mask: SegmentationMask): IntArray {
        @ColorInt val colors =
            IntArray(mask.width * mask.height)
        for (i in 0 until mask.width * mask.height) {
            val backgroundLikelihood = 1 - mask.buffer.float
            if (backgroundLikelihood > 0.9) {
                colors[i] = Color.argb(128, 255, 0, 255)
            } else if (backgroundLikelihood > 0.2) {
                // Linear interpolation to make sure when backgroundLikelihood is 0.2, the alpha is 0 and
                // when backgroundLikelihood is 0.9, the alpha is 128.
                // +0.5 to round the float value to the nearest int.
                val alpha = (182.9 * backgroundLikelihood - 36.6 + 0.5).toInt()
                colors[i] = Color.argb(alpha, 255, 0, 255)
            }
        }
        return colors
    }
}