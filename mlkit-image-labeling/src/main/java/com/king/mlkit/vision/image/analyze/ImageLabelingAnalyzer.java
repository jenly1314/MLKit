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
package com.king.mlkit.vision.image.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabelerOptionsBase;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 图像标签分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ImageLabelingAnalyzer extends CommonAnalyzer<List<ImageLabel>> {

    private final ImageLabeler mDetector;

    public ImageLabelingAnalyzer() {
        this(null);
    }

    public ImageLabelingAnalyzer(ImageLabelerOptionsBase options) {
        if (options != null) {
            mDetector = ImageLabeling.getClient(options);
        } else {
            mDetector = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        }
    }

    @NonNull
    @Override
    protected Task<List<ImageLabel>> detectInImage(@NonNull InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
