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
package com.king.mlkit.vision.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import com.king.mlkit.vision.camera.analyze.Analyzer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BarcodeDecoder {

    private BarcodeDecoder(){
        throw new AssertionError();
    }

    public static InputImage fromFilePath(Context context, Uri uri) throws IOException {
        return InputImage.fromFilePath(context,uri);
    }

    public static InputImage fromBitmap(Bitmap bitmap){
        return fromBitmap(bitmap,0);
    }

    public static InputImage fromBitmap(Bitmap bitmap,int rotation){
        return InputImage.fromBitmap(bitmap,rotation);
    }

    public static Task<List<Barcode>> process(Bitmap bitmap, Analyzer.OnAnalyzeListener<List<Barcode>> listener){
        return BarcodeScanning.getClient().process(fromBitmap(bitmap)).addOnSuccessListener(result -> {
            if(listener != null){
                if(result == null || result.isEmpty()){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }
    
        }).addOnFailureListener(e -> {
            if(listener != null){
                listener.onFailure();
            }
        });
    }

    public static Task<List<Barcode>> process(Bitmap bitmap, @NonNull Executor executor,Analyzer.OnAnalyzeListener<List<Barcode>> listener){
        return BarcodeScanning.getClient().process(fromBitmap(bitmap)).addOnSuccessListener(executor,result -> {
            if(listener != null){
                if(result == null || result.isEmpty()){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }

        }).addOnFailureListener(e -> {
            if(listener != null){
                listener.onFailure();
            }
        });
    }

    public static Task<List<Barcode>> process(Bitmap bitmap){
        return BarcodeScanning.getClient().process(fromBitmap(bitmap));
    }

    public static Task<List<Barcode>> process(Bitmap bitmap,@NonNull BarcodeScannerOptions options){
        return BarcodeScanning.getClient(options).process(fromBitmap(bitmap));
    }

    public static Task<List<Barcode>> process(Bitmap bitmap, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(fromBitmap(bitmap));
    }

    public static Task<List<Barcode>> process(InputImage inputImage,Analyzer.OnAnalyzeListener<List<Barcode>> listener){
        return BarcodeScanning.getClient().process(inputImage).addOnSuccessListener(result -> {
            if(listener != null){
                if(result == null || result.isEmpty()){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }

        }).addOnFailureListener(e -> {
            if(listener != null){
                listener.onFailure();
            }
        });
    }

    public static Task<List<Barcode>> process(InputImage inputImage, @NonNull Executor executor,Analyzer.OnAnalyzeListener<List<Barcode>> listener){
        return BarcodeScanning.getClient().process(inputImage).addOnSuccessListener(executor,result -> {
            if(listener != null){
                if(result == null ){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }

        }).addOnFailureListener(e -> {
            if(listener != null){
                listener.onFailure();
            }
        });
    }

    public static Task<List<Barcode>> process(InputImage inputImage){
        return BarcodeScanning.getClient().process(inputImage);
    }

    public static Task<List<Barcode>> process(InputImage inputImage,@NonNull BarcodeScannerOptions options){
        return BarcodeScanning.getClient(options).process(inputImage);
    }

    public static Task<List<Barcode>> process(InputImage inputImage, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(inputImage);
    }
}
