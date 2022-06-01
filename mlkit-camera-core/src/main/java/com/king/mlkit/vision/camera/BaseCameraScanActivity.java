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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.LogUtils;
import com.king.mlkit.vision.camera.util.PermissionUtils;


/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseCameraScanActivity<T> extends AppCompatActivity implements CameraScan.OnScanResultCallback<T>{

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0X86;

    protected PreviewView previewView;
    private CameraScan<T> mCameraScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isContentView()){
            setContentView(getLayoutId());
        }
        initUI();
    }

    /**
     * 初始化
     */
    public void initUI(){
        previewView = findViewById(getPreviewViewId());

        initCameraScan();
        startCamera();
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
            if(PermissionUtils.checkPermission(this,Manifest.permission.CAMERA)){
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
     * @return 默认返回true
     */
    public boolean isContentView(){
        return true;
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