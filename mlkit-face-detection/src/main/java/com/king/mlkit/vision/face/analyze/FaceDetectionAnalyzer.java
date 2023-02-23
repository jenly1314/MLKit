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
package com.king.mlkit.vision.face.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 人脸检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class FaceDetectionAnalyzer extends CommonAnalyzer<List<Face>> {

    private FaceDetector mDetector;

    public FaceDetectionAnalyzer() {
        this(null);
    }

    public FaceDetectionAnalyzer(FaceDetectorOptions options) {
        if (options != null) {
            mDetector = FaceDetection.getClient(options);
        } else {
            // High-accuracy landmark detection and face classification
//            mDetector = FaceDetection.getClient(new FaceDetectorOptions.Builder()
//                    .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
//                    .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
//                    .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
//                    .build());
            // Real-time contour detection
            mDetector = FaceDetection.getClient(new FaceDetectorOptions.Builder()
                    .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                    .build());
        }
    }

    @NonNull
    @Override
    protected Task<List<Face>> detectInImage(InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
