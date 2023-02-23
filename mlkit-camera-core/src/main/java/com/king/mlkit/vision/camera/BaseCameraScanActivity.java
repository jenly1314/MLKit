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
package com.king.mlkit.vision.camera;

import android.Manifest;
import android.os.Bundle;

import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.LogUtils;
import com.king.mlkit.vision.camera.util.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

/**
 * 相机扫描基类；{@link BaseCameraScanActivity} 内部持有{@link CameraScan}，便于快速实现扫描识别。
 * <p>
 * 快速实现扫描识别主要有以下几种方式：
 * <p>
 * 1、通过继承 {@linkBaseCameraScanActivity}或者{@link BaseCameraScanFragment}或其子类，可快速实现扫描识别。
 * （适用于大多数场景，自定义布局时需覆写getLayoutId方法）
 * <p>
 * 2、在你项目的Activity或者Fragment中实例化一个{@link BaseCameraScan}。（适用于想在扫码界面写交互逻辑，又因为项目
 * 架构或其它原因，无法直接或间接继承{@link BaseCameraScanActivity}或{@link BaseCameraScanFragment}时使用）
 * <p>
 * 3、继承{@link CameraScan}自己实现一个，可参照默认实现类{@link BaseCameraScan}，其他步骤同方式2。（高级用法，谨慎使用）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseCameraScanActivity<T> extends AppCompatActivity implements CameraScan.OnScanResultCallback<T> {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0X86;

    protected PreviewView previewView;
    private CameraScan<T> mCameraScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isContentView()) {
            setContentView(getLayoutId());
        }
        initUI();
    }

    /**
     * 初始化
     */
    public void initUI() {
        previewView = findViewById(getPreviewViewId());

        initCameraScan();
        startCamera();
    }

    /**
     * 初始化CameraScan
     */
    public void initCameraScan() {
        mCameraScan = createCameraScan(previewView)
                .setAnalyzer(createAnalyzer())
                .setOnScanResultCallback(this);
    }

    /**
     * 启动相机预览
     */
    public void startCamera() {
        if (mCameraScan != null) {
            if (PermissionUtils.checkPermission(this, Manifest.permission.CAMERA)) {
                mCameraScan.startCamera();
            } else {
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED");
                PermissionUtils.requestPermission(this, Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
    }

    /**
     * 释放相机
     */
    private void releaseCamera() {
        if (mCameraScan != null) {
            mCameraScan.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            requestCameraPermissionResult(permissions, grantResults);
        }
    }

    /**
     * 请求Camera权限回调结果
     *
     * @param permissions
     * @param grantResults
     */
    public void requestCameraPermissionResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.requestPermissionsResult(Manifest.permission.CAMERA, permissions, grantResults)) {
            startCamera();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        releaseCamera();
        super.onDestroy();
    }

    /**
     * 返回true时会自动初始化{@link #setContentView(int)}，返回为false是需自己去初始化{@link #setContentView(int)}
     *
     * @return 默认返回true
     */
    public boolean isContentView() {
        return true;
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
     * @return
     */
    public int getLayoutId() {
        return R.layout.ml_camera_scan;
    }

    /**
     * 预览界面{@link #previewView}的ID
     *
     * @return
     */
    public int getPreviewViewId() {
        return R.id.previewView;
    }

    /**
     * 获取 {@link CameraScan}
     *
     * @return {@link #mCameraScan}
     */
    public CameraScan<T> getCameraScan() {
        return mCameraScan;
    }

    /**
     * 创建{@link CameraScan}
     *
     * @param previewView
     * @return
     */
    public CameraScan<T> createCameraScan(PreviewView previewView) {
        return new BaseCameraScan<>(this, previewView);
    }

    /**
     * 创建分析器
     *
     * @return
     */
    @Nullable
    public abstract Analyzer<T> createAnalyzer();
}