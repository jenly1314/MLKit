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
package com.king.mlkit.vision.camera.config;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Size;

import com.king.mlkit.vision.camera.util.LogUtils;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;

/**
 * 相机配置：根据尺寸配置相机的目标图像，使输出分析的图像尽可能的接近屏幕尺寸
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ResolutionCameraConfig extends CameraConfig {

    private Size mTargetSize;
    
    public ResolutionCameraConfig(Context context) {
        super();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        LogUtils.d(String.format("displayMetrics:%d x %d",width,height));
        //因为为了保持流畅性和性能，限制在1080p，在此前提下尽可能的找到屏幕接近的分辨率
        if(width < height){
            int size = Math.min(width, 1080);
            float ratio =  width / (float)height;
            if(ratio > 0.7){//一般应用于平板
                mTargetSize = new Size(size, (int)(size / 3.0f * 4.0f));
            }else{
                mTargetSize = new Size(size, (int)(size / 9.0f * 16.0f));
            }
        }else{
            int size = Math.min(height, 1080);
            float ratio = height / (float)width;
            if(ratio > 0.7){//一般应用于平板
                mTargetSize = new Size((int)(size / 3.0f * 4.0f), size);
            }else{
                mTargetSize = new Size((int)(size / 9.0f * 16.0), size);
            }
        }
        LogUtils.d("targetSize:" + mTargetSize);
    }



    @NonNull
    @Override
    public Preview options(@NonNull Preview.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public CameraSelector options(@NonNull CameraSelector.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public ImageAnalysis options(@NonNull ImageAnalysis.Builder builder) {
        builder.setTargetResolution(mTargetSize);
        return super.options(builder);
    }
}
