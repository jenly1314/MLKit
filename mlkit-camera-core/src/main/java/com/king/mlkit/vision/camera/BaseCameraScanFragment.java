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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.LogUtils;
import com.king.mlkit.vision.camera.util.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.Fragment;

/**
 * 相机扫描基类；{@link BaseCameraScanFragment} 内部持有{@link CameraScan}，便于快速实现扫描识别。
 * <p>
 * 快速实现扫描识别主要有以下几种方式：
 * <p>
 * 1、通过继承 {@link BaseCameraScanActivity}或者{@link BaseCameraScanFragment}或其子类，可快速实现扫描识别。
 * （适用于大多数场景，自定义布局时需覆写getLayoutId方法）
 * <p>
 * 2、在你项目的Activity或者Fragment中实例化一个{@link BaseCameraScan}。（适用于想在扫描界面写交互逻辑，又因为项目
 * 架构或其它原因，无法直接或间接继承{@link BaseCameraScanActivity}或{@link BaseCameraScanFragment}时使用）
 * <p>
 * 3、继承{@link CameraScan}自己实现一个，可参照默认实现类{@link BaseCameraScan}，其他步骤同方式2。（高级用法，谨慎使用）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseCameraScanFragment<T> extends Fragment implements CameraScan.OnScanResultCallback<T> {

    /**
     * 相机权限请求代码
     */
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0X86;

    /**
     * 根视图
     */
    private View mRootView;
    /**
     * 预览视图
     */
    protected PreviewView previewView;
    /**
     * CameraScan
     */
    private CameraScan<T> mCameraScan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (isContentView()) {
            mRootView = createRootView(inflater, container);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    /**
     * 初始化
     */
    public void initUI() {
        previewView = mRootView.findViewById(getPreviewViewId());
        mCameraScan = createCameraScan(previewView);
        initCameraScan(mCameraScan);
        startCamera();
    }

    /**
     * 初始化CameraScan
     */
    public void initCameraScan(@NonNull CameraScan<T> cameraScan) {
        cameraScan.setAnalyzer(createAnalyzer())
                .setOnScanResultCallback(this);
        initCameraScan();
    }

    /**
     * 初始化CameraScan
     *
     * @Deprecated 此方法已废弃，后续可能移除；请使用 {@link #initCameraScan(CameraScan)}
     */
    @Deprecated
    public void initCameraScan() {

    }

    /**
     * 启动相机预览
     */
    public void startCamera() {
        if (mCameraScan != null) {
            if (PermissionUtils.checkPermission(getContext(), Manifest.permission.CAMERA)) {
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
     * @param permissions 权限
     * @param grantResults 授权结果
     */
    public void requestCameraPermissionResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.requestPermissionsResult(Manifest.permission.CAMERA, permissions, grantResults)) {
            startCamera();
        } else {
            getActivity().finish();
        }
    }

    @Override
    public void onDestroyView() {
        releaseCamera();
        super.onDestroyView();
    }

    /**
     * 返回true时会自动初始化{@link #createRootView(LayoutInflater, ViewGroup)}，返回为false是需自己去初始化{@link #createRootView(LayoutInflater, ViewGroup)}
     *
     * @return 默认返回true
     */
    public boolean isContentView() {
        return true;
    }

    /**
     * 创建{@link #mRootView}
     *
     * @param inflater  {@link LayoutInflater}
     * @param container {@link ViewGroup}
     * @return 返回创建的根视图
     */
    @NonNull
    public View createRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    /**
     * 布局ID；通过覆写此方法可以自定义布局
     *
     * @return 布局ID
     */
    public int getLayoutId() {
        return R.layout.ml_camera_scan;
    }

    /**
     * 预览视图{@link #previewView}的ID
     *
     * @return 预览视图ID
     */
    public int getPreviewViewId() {
        return R.id.previewView;
    }

    /**
     * 获取{@link CameraScan}
     *
     * @return {@link #mCameraScan}
     */
    public CameraScan<T> getCameraScan() {
        return mCameraScan;
    }

    /**
     * 获取根视图
     *
     * @return {@link #mRootView}
     */
    public View getRootView() {
        return mRootView;
    }

    /**
     * 创建{@link CameraScan}
     *
     * @param previewView
     * @return {@link CameraScan}
     */
    @NonNull
    public CameraScan<T> createCameraScan(PreviewView previewView) {
        return new BaseCameraScan<>(this, previewView);
    }

    /**
     * 创建分析器
     *
     * @return {@link Analyzer}
     */
    @Nullable
    public abstract Analyzer<T> createAnalyzer();

}
