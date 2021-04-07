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

import androidx.annotation.Nullable;

import com.google.mlkit.vision.segmentation.SegmentationMask;
import com.king.mlkit.vision.camera.BaseCameraScanActivity;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class SegmentationCameraScanActivity extends BaseCameraScanActivity<SegmentationMask> {

    @Nullable
    @Override
    public Analyzer<SegmentationMask> createAnalyzer() {
        return new SegmentationAnalyzer();
    }
}
