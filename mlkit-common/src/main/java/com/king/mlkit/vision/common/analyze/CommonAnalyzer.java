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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.king.camera.scan.AnalyzeResult;
import com.king.camera.scan.FrameMetadata;
import com.king.camera.scan.analyze.Analyzer;
import com.king.camera.scan.util.ImageUtils;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通用分析器：将相机预览帧数据分析的通用业务进行统一处理，从而简化各子类的实现；（适用于MLKit各个字库）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class CommonAnalyzer<T> implements Analyzer<T> {

    private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

    private final AtomicBoolean joinQueue = new AtomicBoolean(false);

    /**
     * 检测图像
     * <p>
     * MLKit的各个子库只需实现此方法即可；通常为：{@code return detector.process(inputImage)}
     *
     * @param inputImage {@link InputImage}
     * @return {@link Task}
     */
    @NonNull
    protected abstract Task<T> detectInImage(@NonNull InputImage inputImage);

    @Override
    public void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<T> listener) {
        if (!joinQueue.get()) {
            int imageSize = imageProxy.getWidth() * imageProxy.getHeight();
            byte[] bytes = new byte[imageSize + 2 * (imageSize / 4)];
            queue.add(bytes);
            joinQueue.set(true);
        }
        final byte[] nv21Data = queue.poll();
        if(nv21Data == null) {
            return;
        }
        try {
            ImageUtils.yuv_420_888toNv21(imageProxy, nv21Data);
            InputImage inputImage = InputImage.fromByteArray(
                    nv21Data,
                    imageProxy.getWidth(),
                    imageProxy.getHeight(),
                    imageProxy.getImageInfo().getRotationDegrees(),
                    InputImage.IMAGE_FORMAT_NV21
            );
            // 检测分析
            detectInImage(inputImage).addOnSuccessListener(result -> {
                if (isNullOrEmpty(result)) {
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
     * 是否为空
     */
    private boolean isNullOrEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        return false;
    }
}
