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
package com.king.mlkit.vision.object.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 对象检测分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ObjectDetectionAnalyzer extends CommonAnalyzer<List<DetectedObject>> {

    private final ObjectDetector mDetector;

    public ObjectDetectionAnalyzer() {
        this(null);
    }

    public ObjectDetectionAnalyzer(ObjectDetectorOptionsBase options) {
        if (options != null) {
            mDetector = ObjectDetection.getClient(options);
        } else {
            // Live detection and tracking
            mDetector = ObjectDetection.getClient(new ObjectDetectorOptions.Builder()
                    .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                    .enableClassification()  // Optional
                    .build());

            // Multiple object detection in static images
//            mDetector = ObjectDetection.getClient(new ObjectDetectorOptions.Builder()
//                    .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
//                    .enableMultipleObjects()
//                    .enableClassification()  // Optional
//                    .build());
        }
    }

    @NonNull
    @Override
    protected Task<List<DetectedObject>> detectInImage(@NonNull InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
