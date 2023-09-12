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

import android.view.View;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.king.camera.scan.BaseCameraScanFragment;
import com.king.camera.scan.analyze.Analyzer;
import com.king.mlkit.vision.barcode.analyze.BarcodeScanningAnalyzer;
import com.king.view.viewfinderview.ViewfinderView;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 条形码扫描 - 相机扫描基类
 * <p>
 * 通过继承 {@link BarcodeCameraScanActivity}或{@link BarcodeCameraScanFragment}可快速实现条形码（二维码是条形码的子集）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BarcodeCameraScanFragment extends BaseCameraScanFragment<List<Barcode>> {

    protected ViewfinderView viewfinderView;

    @Override
    public void initUI() {
        int viewfinderViewId = getViewfinderViewId();
        if (viewfinderViewId != View.NO_ID && viewfinderViewId != 0) {
            viewfinderView = getRootView().findViewById(viewfinderViewId);
        }
        super.initUI();
    }

    /**
     * 创建分析器，默认分析所有条码格式
     *
     * @return {@link Analyzer}
     */
    @Nullable
    @Override
    public Analyzer<List<Barcode>> createAnalyzer() {
        return new BarcodeScanningAnalyzer(Barcode.FORMAT_ALL_FORMATS);
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
     * @return 布局ID
     */
    @Override
    public int getLayoutId() {
        return R.layout.ml_barcode_camera_scan;
    }

    /**
     * {@link #viewfinderView} 的 ID
     *
     * @return 默认返回{@code R.id.viewfinderView}, 如果不需要扫码框可以返回{@link View#NO_ID}
     */

    public int getViewfinderViewId() {
        return R.id.viewfinderView;
    }

}
