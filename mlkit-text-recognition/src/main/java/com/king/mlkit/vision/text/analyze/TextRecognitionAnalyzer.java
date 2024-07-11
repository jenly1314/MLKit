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
package com.king.mlkit.vision.text.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import androidx.annotation.NonNull;

/**
 * 文字识别分析器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class TextRecognitionAnalyzer extends CommonAnalyzer<Text> {

    private final TextRecognizer mDetector;

    public TextRecognitionAnalyzer() {
        this(null);
    }

    public TextRecognitionAnalyzer(TextRecognizerOptionsInterface options) {
        if (options != null) {
            mDetector = TextRecognition.getClient(options);
        } else {
            mDetector = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        }
    }

    @NonNull
    @Override
    protected Task<Text> detectInImage(InputImage inputImage) {
        return mDetector.process(inputImage);
    }
}
