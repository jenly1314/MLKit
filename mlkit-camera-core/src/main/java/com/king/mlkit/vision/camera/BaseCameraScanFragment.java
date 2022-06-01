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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.Fragment;

import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.LogUtils;
import com.king.mlkit.vision.camera.util.PermissionUtils;


/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseCameraScanFragment<T> extends Fragment implements CameraScan.OnScanResultCallback<T> {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0X86;

    private View mRootView;

    protected PreviewView previewView;
    protected View ivFlashlight;

    private CameraScan mCameraScan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(isContentView()){
            mRootView = createRootView(inflater,container);
        }
        initUI();
        return mRootView;
    }

    /**
     * 初始化
     */
    public void initUI(){
        previewView = mRootView.findViewById(getPreviewViewId());
        initCameraScan();
        startCamera();
    }

    /**
     * 点击手电筒
     */
    protected void onClickFlashlight(){
        toggleTorchState();
    }

    /**
     * 初始化CameraScan
     */
    public void initCameraScan(){
        mCameraScan = createCameraScan(previewView)
                .setAnalyzer(createAnalyzer())
                .setOnScanResultCallback(this);
    }

    /**
     * 启动相机预览
     */
    public void startCamera(){
        if(mCameraScan != null){
            if(PermissionUtils.checkPermission(getContext(), Manifest.permission.CAMERA)){
                mCameraScan.startCamera();
            }else{
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED");
                PermissionUtils.requestPermission(this,Manifest.permission.CAMERA,CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
    }

    /**
     * 释放相机
     */
    private void releaseCamera(){
        if(mCameraScan != null){
            mCameraScan.release();
        }
    }

    /**
     * 切换闪光灯状态（开启/关闭）
     */
    protected void toggleTorchState(){
        if(mCameraScan != null){
            boolean isTorch = mCameraScan.isTorchEnabled();
            mCameraScan.enableTorch(!isTorch);
            if(ivFlashlight != null){
                ivFlashlight.setSelected(!isTorch);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            requestCameraPermissionResult(permissions,grantResults);
        }
    }

    /**
     * 请求Camera权限回调结果
     * @param permissions
     * @param grantResults
     */
    public void requestCameraPermissionResult(@NonNull String[] permissions, @NonNull int[] grantResults){
        if(PermissionUtils.requestPermissionsResult(Manifest.permission.CAMERA,permissions,grantResults)){
            startCamera();
        }else{
            getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        releaseCamera();
        super.onDestroy();
    }

    /**
     * 返回true时会自动初始化{@link #createRootView(LayoutInflater, ViewGroup)}，返回为false是需自己去初始化{@link #createRootView(LayoutInflater, ViewGroup)}
     * @return 默认返回true
     */
    public boolean isContentView(){
        return true;
    }

    /**
     * 创建{@link #mRootView}
     * @param inflater
     * @param container
     * @return
     */
    @NonNull
    public View createRootView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(getLayoutId(),container,false);
    }

    /**
     * 布局id
     * @return
     */
    public int getLayoutId(){
        return R.layout.ml_camera_scan;
    }


    /**
     * 预览界面{@link #previewView} 的ID
     * @return
     */
    public int getPreviewViewId(){
        return R.id.previewView;
    }

    /**
     * Get {@link CameraScan}
     * @return {@link #mCameraScan}
     */
    public CameraScan<T> getCameraScan(){
        return mCameraScan;
    }


    //--------------------------------------------

    public View getRootView() {
        return mRootView;
    }

    /**
     * 创建{@link CameraScan}
     * @param previewView
     * @return
     */
    public CameraScan<T> createCameraScan(PreviewView previewView){
        return new BaseCameraScan<>(this,previewView);
    }

    /**
     * 创建分析器
     * @return
     */
    @Nullable
    public abstract Analyzer<T> createAnalyzer();

}
