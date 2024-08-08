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

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 条码解码器：主要用于检测图像上的条形码/二维码
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BarcodeDecoder {

    private BarcodeDecoder() {
        throw new AssertionError();
    }


    @NonNull
    public static InputImage fromFilePath(@NonNull Context context, @NonNull Uri uri) throws IOException {
        return InputImage.fromFilePath(context, uri);
    }

    @NonNull
    public static InputImage fromBitmap(@NonNull Bitmap bitmap) {
        return fromBitmap(bitmap, 0);
    }

    @NonNull
    public static InputImage fromBitmap(@NonNull Bitmap bitmap, int rotation) {
        return InputImage.fromBitmap(bitmap, rotation);
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull Bitmap bitmap) {
        return process(fromBitmap(bitmap), Barcode.FORMAT_ALL_FORMATS);
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull Bitmap bitmap, @NonNull BarcodeScannerOptions options) {
        return process(fromBitmap(bitmap), options);
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull Bitmap bitmap, @Barcode.BarcodeFormat int format, @Barcode.BarcodeFormat int... formats) {
        return process(fromBitmap(bitmap), format, formats);
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull InputImage inputImage) {
        return process(inputImage, Barcode.FORMAT_ALL_FORMATS);
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull InputImage inputImage, @Barcode.BarcodeFormat int format, @Barcode.BarcodeFormat int... formats) {
        return process(inputImage, new BarcodeScannerOptions.Builder().setBarcodeFormats(format, formats).build());
    }

    @NonNull
    public static Task<List<Barcode>> process(@NonNull InputImage inputImage, @NonNull BarcodeScannerOptions options) {
        return BarcodeScanning.getClient(options).process(inputImage);
    }

    /**
     * 获取条码信息，通过遍历{@param barcodeList}找到与正则{@param regex}匹配的条码
     *
     * @param barcodeList  条码列表
     * @param regex        正则
     * @param allowDefault 是否允许默认返回
     *                     为true表示允许，则在没有找到与正则匹配的的条码时，默认返回第0个位置的条码
     *                     为false表示不允许，则在没有找到与正则匹配的条码识，返回空
     * @return {@link Barcode}
     */
    @Nullable
    public static Barcode getBarcode(@Nullable List<Barcode> barcodeList, @Nullable String regex, boolean allowDefault) {
        if (barcodeList != null && !barcodeList.isEmpty()) {
            if (!TextUtils.isEmpty(regex)) {
                // 如果正则不为空
                for (Barcode barcode : barcodeList) {
                    if (Pattern.matches(regex, barcode.getRawValue())) {
                        // 通过遍历找到与正则匹配的条码
                        return barcode;
                    }
                }
            }
            if (allowDefault) {
                // 如果允许默认，则在没有找到与正则匹配的的条码时，默认返回第0个位置的条码
                return barcodeList.get(0);
            }
        }

        return null;
    }
}
