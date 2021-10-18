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

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabelerOptionsBase;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.king.mlkit.vision.camera.AnalyzeResult;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.BitmapUtils;
import com.king.mlkit.vision.camera.util.LogUtils;

import java.util.List;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ImageLabelingAnalyzer implements Analyzer<List<ImageLabel>> {

    private ImageLabeler mDetector;

    public ImageLabelingAnalyzer(){
        this(null);
    }

    public ImageLabelingAnalyzer(ImageLabelerOptionsBase options){
        if(options != null){
            mDetector = ImageLabeling.getClient(options);
        }else{
            mDetector = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        }
    }


    @Override
    public void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<AnalyzeResult<List<ImageLabel>>> listener) {
        try {

            final Bitmap bitmap = BitmapUtils.getBitmap(imageProxy);
//            final Bitmap bitmap = ImageUtils.imageProxyToBitmap(imageProxy);
//            @SuppressLint("UnsafeExperimentalUsageError")
//            InputImage inputImage = InputImage.fromMediaImage(imageProxy.getImage(),imageProxy.getImageInfo().getRotationDegrees());
            InputImage inputImage = InputImage.fromBitmap(bitmap,0);

            mDetector.process(inputImage).addOnSuccessListener(result -> {
                if(result == null || result.isEmpty()){
                    listener.onFailure();
                }else{
                    listener.onSuccess(new AnalyzeResult<>(bitmap,result));
                }
            }).addOnFailureListener(e -> {
                listener.onFailure();
            });
        } catch (Exception e) {
            LogUtils.w(e);
        }
    }
}
