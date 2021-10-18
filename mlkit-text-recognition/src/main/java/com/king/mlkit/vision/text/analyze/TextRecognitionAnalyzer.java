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

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.king.mlkit.vision.camera.AnalyzeResult;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.BitmapUtils;
import com.king.mlkit.vision.camera.util.LogUtils;


/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class TextRecognitionAnalyzer implements Analyzer<Text> {

    private TextRecognizer mTextRecognizer;

    public TextRecognitionAnalyzer() {
        this(null);
    }

    public TextRecognitionAnalyzer(TextRecognizerOptionsInterface options){
        if(options != null){
            mTextRecognizer = TextRecognition.getClient(options);
        }else{
            mTextRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        }
    }


    @Override
    public void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<AnalyzeResult<Text>> listener) {
        try {

            final Bitmap bitmap = BitmapUtils.getBitmap(imageProxy);
//            final Bitmap bitmap = ImageUtils.imageProxyToBitmap(imageProxy);
//            @SuppressLint("UnsafeExperimentalUsageError")
//            InputImage inputImage = InputImage.fromMediaImage(imageProxy.getImage(),imageProxy.getImageInfo().getRotationDegrees());
            InputImage inputImage = InputImage.fromBitmap(bitmap,0);

            mTextRecognizer.process(inputImage).addOnSuccessListener(result -> {
                if(result != null){
                    listener.onSuccess(new AnalyzeResult<>(bitmap,result));
                }else {
                    listener.onFailure();
                }
            }).addOnFailureListener(e -> {
                listener.onFailure();
            });
        }catch (Exception e){
            LogUtils.w(e);
        }
    }
}
