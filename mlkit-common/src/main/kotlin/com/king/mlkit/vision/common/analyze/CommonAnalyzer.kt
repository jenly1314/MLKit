package com.king.mlkit.vision.common.analyze

import android.graphics.ImageFormat
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.king.camera.scan.AnalyzeResult
import com.king.camera.scan.FrameMetadata
import com.king.camera.scan.analyze.Analyzer
import com.king.camera.scan.util.ImageUtils
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 通用分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
abstract class CommonAnalyzer<T : Any> : Analyzer<T> {

    private val queue: Queue<ByteArray> = ConcurrentLinkedQueue()
    private val joinQueue = AtomicBoolean(false)

    protected abstract fun detectInImage(inputImage: InputImage): Task<T>

    override fun analyze(imageProxy: ImageProxy, listener: Analyzer.OnAnalyzeListener<T>) {
        if (!joinQueue.get()) {
            val imageSize = imageProxy.width * imageProxy.height
            val bytes = ByteArray(imageSize + 2 * (imageSize / 4))
            queue.add(bytes)
            joinQueue.set(true)
        }
        val nv21Data = queue.poll() ?: return
        try {
            ImageUtils.yuv_420_888toNv21(imageProxy, nv21Data)
            val inputImage = InputImage.fromByteArray(
                nv21Data,
                imageProxy.width,
                imageProxy.height,
                imageProxy.imageInfo.rotationDegrees,
                InputImage.IMAGE_FORMAT_NV21
            )
            detectInImage(inputImage)
                .addOnSuccessListener { result ->
                    if (isNullOrEmpty(result)) {
                        queue.add(nv21Data)
                        listener.onFailure(null)
                    } else {
                        val frameMetadata = FrameMetadata(
                            imageProxy.width,
                            imageProxy.height,
                            imageProxy.imageInfo.rotationDegrees
                        )
                        joinQueue.set(false)
                        listener.onSuccess(AnalyzeResult(nv21Data, ImageFormat.NV21, frameMetadata, result))
                    }
                }
                .addOnFailureListener { e ->
                    queue.add(nv21Data)
                    listener.onFailure(e)
                }
        } catch (e: Exception) {
            queue.add(nv21Data)
            listener.onFailure(e)
        }
    }

    private fun isNullOrEmpty(obj: Any?): Boolean {
        if (obj == null) return true
        if (obj is Collection<*>) return obj.isEmpty()
        return false
    }
}
