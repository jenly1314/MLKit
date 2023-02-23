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

import android.graphics.Bitmap;

/**
 * 分析结果
 *
 * @param <T> 泛型T为分析结果的具体数据类型
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AnalyzeResult<T> {
    /**
     * 分析的原始图像
     */
    private Bitmap bitmap;
    /**
     * 分析图像得到的结果
     */
    private T result;

    public AnalyzeResult(Bitmap bitmap, T result) {
        this.bitmap = bitmap;
        this.result = result;
    }

    /**
     * 获取分析的原始图像
     *
     * @return
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Deprecated
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * 获取分析结果
     *
     * @return
     */
    public T getResult() {
        return result;
    }

    @Deprecated
    public void setResult(T result) {
        this.result = result;
    }

}
