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
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.king.mlkit.vision.camera.analyze.Analyzer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

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
        return process(bitmap,listener, Barcode.FORMAT_ALL_FORMATS);
    }

    public static Task<List<Barcode>> process(Bitmap bitmap, Analyzer.OnAnalyzeListener<List<Barcode>> listener, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(fromBitmap(bitmap)).addOnSuccessListener(result -> {
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
        return process(bitmap,executor,listener, Barcode.FORMAT_ALL_FORMATS);
    }

    public static Task<List<Barcode>> process(Bitmap bitmap, @NonNull Executor executor,Analyzer.OnAnalyzeListener<List<Barcode>> listener, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(fromBitmap(bitmap)).addOnSuccessListener(executor,result -> {
            if(listener != null){
                if(result == null || result.isEmpty()){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }

        }).addOnFailureListener(executor, e -> {
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
        return process(inputImage,listener, Barcode.FORMAT_ALL_FORMATS);
    }


    public static Task<List<Barcode>> process(InputImage inputImage,Analyzer.OnAnalyzeListener<List<Barcode>> listener, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(inputImage).addOnSuccessListener(result -> {
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
        return process(inputImage,executor,listener, Barcode.FORMAT_ALL_FORMATS);
    }

    public static Task<List<Barcode>> process(InputImage inputImage, @NonNull Executor executor,Analyzer.OnAnalyzeListener<List<Barcode>> listener, @Barcode.BarcodeFormat int format,@Barcode.BarcodeFormat int... formats){
        return BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(format,formats).build()).process(inputImage).addOnSuccessListener(executor,result -> {
            if(listener != null){
                if(result == null ){
                    listener.onFailure();
                }else{
                    listener.onSuccess(result);
                }
            }

        }).addOnFailureListener(executor, e -> {
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

    /**
     * 获取条码信息，通过遍历{@param barcodeList}找到与正则{@param regex}匹配的条码
     * @param barcodeList 条码列表
     * @param regex 正则
     * @param allowDefault 是否允许默认返回
     *                     为true表示允许，则在没有找到与正则匹配的的条码时，默认返回第0个位置的条码
     *                     为false表示不允许，则在没有找到与正则匹配的条码识，返回空
     * @return
     */
    public static Barcode getBarcode(List<Barcode> barcodeList, @Nullable String regex,boolean allowDefault){
        if(barcodeList != null && !barcodeList.isEmpty()){
            if(!TextUtils.isEmpty(regex)){//如果正则不为空
                for(Barcode barcode : barcodeList){
                    if(Pattern.matches(regex,barcode.getRawValue())){//通过遍历找到与正则匹配的条码
                        return barcode;
                    }
                }
            }
            if(allowDefault){//如果允许默认，则在没有找到与正则匹配的的条码时，默认返回第0个位置的条码
                return barcodeList.get(0);
            }
        }

        return null;
    }
}
