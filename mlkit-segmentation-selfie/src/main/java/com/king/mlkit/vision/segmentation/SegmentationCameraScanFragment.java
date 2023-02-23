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
package com.king.mlkit.vision.segmentation;

import com.google.mlkit.vision.segmentation.SegmentationMask;
import com.king.mlkit.vision.camera.BaseCameraScanFragment;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer;

import androidx.annotation.Nullable;

/**
 * 自拍分割 - 相机扫描基类
 * <p>
 * 通过继承 {@link SegmentationCameraScanActivity}或{@link SegmentationCameraScanFragment}可快速实现自拍分割
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class SegmentationCameraScanFragment extends BaseCameraScanFragment<SegmentationMask> {

    @Nullable
    @Override
    public Analyzer<SegmentationMask> createAnalyzer() {
        return new SegmentationAnalyzer();
    }
}
