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

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.king.mlkit.vision.camera.AnalyzeResult;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.BitmapUtils;
import com.king.mlkit.vision.camera.util.LogUtils;

import java.nio.ByteBuffer;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

/**
 * 通用分析器：将相机预览帧数据分析的通用业务进行统一处理，从而简化各子类的实现；（适用于MLKit各个字库）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class CommonAnalyzer<T> implements Analyzer<T> {

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
        try {

            @SuppressLint("UnsafeOptInUsageError") final ByteBuffer nv21Buffer = BitmapUtils.yuv420ThreePlanesToNV21(
                    imageProxy.getImage().getPlanes(),
                    imageProxy.getWidth(),
                    imageProxy.getHeight()
            );
            InputImage inputImage = InputImage.fromByteBuffer(
                    nv21Buffer,
                    imageProxy.getWidth(),
                    imageProxy.getHeight(),
                    imageProxy.getImageInfo().getRotationDegrees(),
                    InputImage.IMAGE_FORMAT_NV21
            );
            // 检测分析
            detectInImage(inputImage).addOnSuccessListener(result -> {
                if (result == null || (result instanceof Collection && ((Collection) result).isEmpty())) {
                    listener.onFailure(null);
                } else {
                    Bitmap bitmap = BitmapUtils.getBitmap(
                            nv21Buffer,
                            imageProxy.getWidth(),
                            imageProxy.getHeight(),
                            imageProxy.getImageInfo().getRotationDegrees()
                    );
                    if(bitmap != null){
                        LogUtils.d("bitmap: " + bitmap.getWidth() + " * " + bitmap.getHeight());
                    }
                    listener.onSuccess(new AnalyzeResult<>(bitmap, result));
                }
            }).addOnFailureListener(e -> {
                listener.onFailure(e);
            });
        } catch (Exception e) {
            LogUtils.w(e);
            listener.onFailure(e);
        }
    }
}
