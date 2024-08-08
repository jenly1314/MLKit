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
package com.king.mlkit.vision.barcode.analyze;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.king.mlkit.vision.common.analyze.CommonAnalyzer;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 条码扫描分析器：分析相机预览的帧数据，从中检测识别条形码/二维码
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BarcodeScanningAnalyzer extends CommonAnalyzer<List<Barcode>> {

    private final BarcodeScanner mDetector;

    public BarcodeScanningAnalyzer() {
        mDetector = BarcodeScanning.getClient();
    }

    public BarcodeScanningAnalyzer(@Barcode.BarcodeFormat int barcodeFormat, @Barcode.BarcodeFormat int... barcodeFormats) {
        this(new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(barcodeFormat, barcodeFormats)
                .build());
    }

    public BarcodeScanningAnalyzer(BarcodeScannerOptions options) {
        if (options != null) {
            mDetector = BarcodeScanning.getClient(options);
        } else {
            mDetector = BarcodeScanning.getClient();
        }
    }

    @NonNull
    @Override
    protected Task<List<Barcode>> detectInImage(@NonNull InputImage inputImage) {
        return mDetector.process(inputImage);
    }

}
