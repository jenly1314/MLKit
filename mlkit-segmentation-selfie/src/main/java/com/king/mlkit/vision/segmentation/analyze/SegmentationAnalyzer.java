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
package com.king.mlkit.vision.segmentation.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.segmentation.Segmentation;
import com.google.mlkit.vision.segmentation.SegmentationMask;
import com.google.mlkit.vision.segmentation.Segmenter;
import com.google.mlkit.vision.segmentation.selfie.SelfieSegmenterOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import androidx.annotation.NonNull;

/**
 * 自拍分割分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class SegmentationAnalyzer extends CommonAnalyzer<SegmentationMask> {

    private Segmenter mDetector;

    public SegmentationAnalyzer() {
        this(null);
    }

    public SegmentationAnalyzer(SelfieSegmenterOptions options) {
        if (options != null) {
            mDetector = Segmentation.getClient(options);
        } else {
            mDetector = Segmentation.getClient(new SelfieSegmenterOptions.Builder()
                    .setDetectorMode(SelfieSegmenterOptions.STREAM_MODE)
                    .enableRawSizeMask()
                    .build());
        }
    }

    @NonNull
    @Override
    protected Task<SegmentationMask> detectInImage(InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
