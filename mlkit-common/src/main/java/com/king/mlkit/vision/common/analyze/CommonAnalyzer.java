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
package com.king.mlkit.vision.common.analyze;

import android.graphics.ImageFormat;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.king.camera.scan.AnalyzeResult;
import com.king.camera.scan.FrameMetadata;
import com.king.camera.scan.analyze.Analyzer;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

/**
 * 通用分析器：将相机预览帧数据分析的通用业务进行统一处理，从而简化各子类的实现；（适用于MLKit各个字库）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class CommonAnalyzer<T> implements Analyzer<T> {

    private Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

    private AtomicBoolean joinQueue = new AtomicBoolean(false);

    /**
     * 检测图像
     * <p>
     * MLKit的各个子库只需实现此方法即可；通常为：{@code return detector.process(inputImage)}
     *
     * @param inputImage {@link InputImage}
     * @return {@link Task}
     */
    @NonNull
    protected abstract Task<T> detectInImage(InputImage inputImage);

    @Override
    public void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<AnalyzeResult<T>> listener) {
        if (!joinQueue.get()) {
            int imageSize = imageProxy.getWidth() * imageProxy.getHeight();
            byte[] bytes = new byte[imageSize + 2 * (imageSize / 4)];
            queue.add(bytes);
            joinQueue.set(true);
        }
        if (queue.isEmpty()) {
            return;
        }
        final byte[] nv21Data = queue.poll();
        try {
            yuv_420_888toNv21(imageProxy, nv21Data);
            InputImage inputImage = InputImage.fromByteArray(
                    nv21Data,
                    imageProxy.getWidth(),
                    imageProxy.getHeight(),
                    imageProxy.getImageInfo().getRotationDegrees(),
                    InputImage.IMAGE_FORMAT_NV21
            );
            // 检测分析
            detectInImage(inputImage).addOnSuccessListener(result -> {
                if (result == null || (result instanceof Collection && ((Collection) result).isEmpty())) {
                    queue.add(nv21Data);
                    listener.onFailure(null);
                } else {
                    FrameMetadata frameMetadata = new FrameMetadata(
                            imageProxy.getWidth(),
                            imageProxy.getHeight(),
                            imageProxy.getImageInfo().getRotationDegrees());
                    joinQueue.set(false);
                    listener.onSuccess(new AnalyzeResult<>(nv21Data, ImageFormat.NV21, frameMetadata, result));
                }
            }).addOnFailureListener(e -> {
                queue.add(nv21Data);
                listener.onFailure(e);
            });
        } catch (Exception e) {
            queue.add(nv21Data);
            listener.onFailure(e);
        }
    }

    /**
     * YUV420_888转NV21
     *
     * @param image
     * @param nv21
     */
    private void yuv_420_888toNv21(@NonNull ImageProxy image, byte[] nv21) {
        ImageProxy.PlaneProxy yPlane = image.getPlanes()[0];
        ImageProxy.PlaneProxy uPlane = image.getPlanes()[1];
        ImageProxy.PlaneProxy vPlane = image.getPlanes()[2];

        ByteBuffer yBuffer = yPlane.getBuffer();
        ByteBuffer uBuffer = uPlane.getBuffer();
        ByteBuffer vBuffer = vPlane.getBuffer();
        yBuffer.rewind();
        uBuffer.rewind();
        vBuffer.rewind();

        int ySize = yBuffer.remaining();

        int position = 0;

        // Add the full y buffer to the array. If rowStride > 1, some padding may be skipped.
        for (int row = 0; row < image.getHeight(); row++) {
            yBuffer.get(nv21, position, image.getWidth());
            position += image.getWidth();
            yBuffer.position(Math.min(ySize, yBuffer.position() - image.getWidth() + yPlane.getRowStride()));
        }

        int chromaHeight = image.getHeight() / 2;
        int chromaWidth = image.getWidth() / 2;
        int vRowStride = vPlane.getRowStride();
        int uRowStride = uPlane.getRowStride();
        int vPixelStride = vPlane.getPixelStride();
        int uPixelStride = uPlane.getPixelStride();

        // Interleave the u and v frames, filling up the rest of the buffer. Use two line buffers to
        // perform faster bulk gets from the byte buffers.
        byte[] vLineBuffer = new byte[vRowStride];
        byte[] uLineBuffer = new byte[uRowStride];
        for (int row = 0; row < chromaHeight; row++) {
            vBuffer.get(vLineBuffer, 0, Math.min(vRowStride, vBuffer.remaining()));
            uBuffer.get(uLineBuffer, 0, Math.min(uRowStride, uBuffer.remaining()));
            int vLineBufferPosition = 0;
            int uLineBufferPosition = 0;
            for (int col = 0; col < chromaWidth; col++) {
                nv21[position++] = vLineBuffer[vLineBufferPosition];
                nv21[position++] = uLineBuffer[uLineBufferPosition];
                vLineBufferPosition += vPixelStride;
                uLineBufferPosition += uPixelStride;
            }
        }
    }
}
