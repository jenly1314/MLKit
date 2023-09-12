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

import androidx.annotation.Nullable;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.king.camera.scan.analyze.Analyzer;
import com.king.mlkit.vision.barcode.analyze.BarcodeScanningAnalyzer;

import java.util.List;

/**
 * 二维码扫描 - 相机扫描基类
 * <p>
 * 通过继承 {@link BarcodeCameraScanActivity}或{@link BarcodeCameraScanFragment}可快速实现二维码扫描
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class QRCodeCameraScanFragment extends BarcodeCameraScanFragment {

    /**
     * 创建分析器，默认分析所有条码格式
     *
     * @return
     */
    @Nullable
    @Override
    public Analyzer<List<Barcode>> createAnalyzer() {
        return new BarcodeScanningAnalyzer(Barcode.FORMAT_QR_CODE);
    }

}
