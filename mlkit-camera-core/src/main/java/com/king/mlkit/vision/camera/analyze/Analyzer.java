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
package com.king.mlkit.vision.camera.analyze;

import com.king.mlkit.vision.camera.AnalyzeResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;

/**
 * 分析器：主要用于分析相机预览的帧数据
 *
 * @param <T> 泛型T为分析成功后的具体结果
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface Analyzer<T> {
    /**
     * Analyzes an image to produce a result.
     *
     * @param imageProxy The image to analyze
     * @param listener   {@link OnAnalyzeListener}
     */
    void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<AnalyzeResult<T>> listener);

    /**
     * Analyze listener
     *
     * @param <T> 泛型T为分析结果对应的对象
     */
    public interface OnAnalyzeListener<T> {
        /**
         * 成功
         *
         * @param result 分析结果
         */
        void onSuccess(@NonNull T result);

        /**
         * 失败
         */
        void onFailure(@Nullable Exception e);
    }
}
