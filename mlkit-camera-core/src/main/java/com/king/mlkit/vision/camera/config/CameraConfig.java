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

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;

/**
 * 相机配置：主要用于提供相机预览时可自定义一些配置，便于拓展；
 * <p>
 * 库中内置实现{@link CameraConfig}的有{@link AspectRatioCameraConfig}和{@link ResolutionCameraConfig}；
 * <p>
 * 这里简单说下各自的特点：
 * <p>
 * {@link CameraConfig} - 默认的相机配置
 * <p>
 * {@link AspectRatioCameraConfig} - 根据纵横比配置相机，使输出分析的图像尽可能的接近屏幕的比例
 * <p>
 * {@link ResolutionCameraConfig} - 根据尺寸配置相机的目标图像大小，使输出分析的图像的分辨率尽可能的接近屏幕尺寸
 * <p>
 * 当使用默认的 {@link CameraConfig}在某些机型上体验欠佳时，你可以尝试使用{@link AspectRatioCameraConfig}或
 * {@link ResolutionCameraConfig}会有意想不到奇效。
 * <p>
 * 你也可以自定义或覆写 {@link CameraConfig} 中的 {@link #options} 方法，根据需要定制配置。
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CameraConfig {

    public CameraConfig() {

    }

    /**
     * 配置 {@link Preview.Builder}；可参考：{@link AspectRatioCameraConfig} 或 {@link ResolutionCameraConfig}
     * <p>
     * 如配置目标旋转角度为90度：{@code builder.setTargetRotation(Surface.ROTATION_90)}
     * <p>
     * 切记，外部请勿直接调用 {@link #options(Preview.Builder)}
     *
     * @param builder
     * @return
     */
    @NonNull
    public Preview options(@NonNull Preview.Builder builder) {
        return builder.build();
    }

    /**
     * 配置 {@link CameraSelector.Builder}；可参考：{@link AspectRatioCameraConfig} 或 {@link ResolutionCameraConfig}
     * <p>
     * 如配置前置摄像头：{@code builder.requireLensFacing(CameraSelector.LENS_FACING_FRONT)}
     * <p>
     * 切记，外部请勿直接调用 {@link #options(CameraSelector.Builder)}
     *
     * @param builder
     * @return
     */
    @NonNull
    public CameraSelector options(@NonNull CameraSelector.Builder builder) {
        return builder.build();
    }

    /**
     * 配置 {@link ImageAnalysis.Builder}；可参考：{@link AspectRatioCameraConfig} 或 {@link ResolutionCameraConfig}
     * <p>
     * 如配置目标旋转角度为90度：{@code builder.setTargetRotation(Surface.ROTATION_90)}
     * <p>
     * 切记，外部请勿直接调用 {@link #options(ImageAnalysis.Builder)}
     *
     * @param builder
     * @return
     */
    @NonNull
    public ImageAnalysis options(@NonNull ImageAnalysis.Builder builder) {
        return builder.build();
    }

}
