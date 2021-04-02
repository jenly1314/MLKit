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
package com.king.mlkit.vision.camera;

import androidx.annotation.FloatRange;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface ICameraControl {

    /**
     * 放大
     */
    void zoomIn();

    /**
     * 缩小
     */
    void zoomOut();

    /**
     * 缩放到指定比例
     * @param ratio
     */
    void zoomTo(float ratio);

    /**
     * 线性放大
     */
    void lineZoomIn();

    /**
     * 线性缩小
     */
    void lineZoomOut();

    /**
     * 线性缩放到指定比例
     * @param linearZoom
     */
    void lineZoomTo(@FloatRange(from = 0.0,to = 1.0) float linearZoom);

    /**
     * 设置闪光灯（手电筒）是否开启
     * @param torch
     */
    void enableTorch(boolean torch);

    /**
     * 闪光灯（手电筒）是否开启
     * @return
     */
    boolean isTorchEnabled();

    /**
     * 是否支持闪光灯
     * @return
     */
    boolean hasFlashUnit();
}
