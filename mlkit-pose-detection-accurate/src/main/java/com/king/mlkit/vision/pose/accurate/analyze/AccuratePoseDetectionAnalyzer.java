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
package com.king.mlkit.vision.pose.accurate.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import androidx.annotation.NonNull;

/**
 * 姿势检测（精确）分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AccuratePoseDetectionAnalyzer extends CommonAnalyzer<Pose> {

    private final PoseDetector mDetector;

    public AccuratePoseDetectionAnalyzer() {
        this(null);
    }

    public AccuratePoseDetectionAnalyzer(PoseDetectorOptionsBase options) {
        if (options != null) {
            mDetector = PoseDetection.getClient(options);
        } else {
            mDetector = PoseDetection.getClient(new AccuratePoseDetectorOptions.Builder()
                    .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
                    .build());
        }
    }

    @NonNull
    @Override
    protected Task<Pose> detectInImage(@NonNull InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
