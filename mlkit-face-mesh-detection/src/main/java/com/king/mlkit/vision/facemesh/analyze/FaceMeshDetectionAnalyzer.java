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
package com.king.mlkit.vision.facemesh.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.facemesh.FaceMesh;
import com.google.mlkit.vision.facemesh.FaceMeshDetection;
import com.google.mlkit.vision.facemesh.FaceMeshDetector;
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 人脸网格分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class FaceMeshDetectionAnalyzer extends CommonAnalyzer<List<FaceMesh>> {

    private final FaceMeshDetector mDetector;

    public FaceMeshDetectionAnalyzer() {
        this(null);
    }

    public FaceMeshDetectionAnalyzer(FaceMeshDetectorOptions options) {
        if (options != null) {
            mDetector = FaceMeshDetection.getClient(options);
        } else {
            mDetector = FaceMeshDetection.getClient();
        }
    }

    @NonNull
    @Override
    protected Task<List<FaceMesh>> detectInImage(InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
