package com.king.mlkit.vision.app.pose

import android.widget.ImageView
import com.google.mlkit.vision.pose.Pose
import com.king.app.dialog.AppDialog
import com.king.app.dialog.AppDialogConfig
import com.king.camera.scan.AnalyzeResult
import com.king.mlkit.vision.app.R
import com.king.mlkit.vision.app.drawRect
import com.king.mlkit.vision.pose.PoseCameraScanActivity

/**
 * 姿势检测示例
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class PoseDetectionActivity : PoseCameraScanActivity() {

    override fun onScanResultCallback(result: AnalyzeResult<Pose>) {
        if (result.result.allPoseLandmarks.isNullOrEmpty()) {
            // 过滤掉空数据
            return
        }
        cameraScan.setAnalyzeImage(false)
        val bitmap = result.bitmap?.drawRect { canvas, paint ->
            for (data in result.result.allPoseLandmarks) {
                canvas.drawCircle(data.position.x, data.position.y, 6f, paint)
            }
        }

//        val pose = result.result
//        // Or get specific PoseLandmarks individually. These will all be null if no person
//        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
//        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
//        val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
//        val rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
//        val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
//        val rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)
//        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
//        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)
//        val leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)
//        val rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)
//        val leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
//        val rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)
//        val leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY)
//        val rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY)
//        val leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX)
//        val rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX)
//        val leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB)
//        val rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB)
//        val leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL)
//        val rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL)
//        val leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)
//        val rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)
//        val nose = pose.getPoseLandmark(PoseLandmark.NOSE)
//        val leftEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER)
//        val leftEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE)
//        val leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER)
//        val rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER)
//        val rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE)
//        val rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER)
//        val leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR)
//        val rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR)
//        val leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH)
//        val rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)

        val config = AppDialogConfig(this, R.layout.result_dialog)
        config.setOnClickConfirm {
            AppDialog.INSTANCE.dismissDialog()
            cameraScan.setAnalyzeImage(true)
        }.setOnClickCancel {
            AppDialog.INSTANCE.dismissDialog()
            finish()
        }
        val imageView = config.getView<ImageView>(R.id.ivDialogContent)
        imageView.setImageBitmap(bitmap)
        AppDialog.INSTANCE.showDialog(config, false)
    }
}