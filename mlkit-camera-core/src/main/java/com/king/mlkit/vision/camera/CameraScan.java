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

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraSelector;

import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.config.CameraConfig;

/**
 * 相机扫描基类定义；内置的默认实现见：{@link BaseCameraScan}
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
public abstract class CameraScan<T> implements ICamera, ICameraControl {

    /**
     * 扫描返回结果的key；解析方式可参见：{@link #parseScanResult(Intent)}
     */
    public static String SCAN_RESULT = "SCAN_RESULT";

    /**
     * A camera on the device facing the same direction as the device's screen.
     */
    public static int LENS_FACING_FRONT = CameraSelector.LENS_FACING_FRONT;
    /**
     * A camera on the device facing the opposite direction as the device's screen.
     */
    public static int LENS_FACING_BACK = CameraSelector.LENS_FACING_BACK;

    /**
     * 纵横比：4:3
     */
    public static final float ASPECT_RATIO_4_3 = 4.0F / 3.0F;
    /**
     * 纵横比：16:9
     */
    public static final float ASPECT_RATIO_16_9 = 16.0F / 9.0F;

    /**
     * 是否需要支持触摸缩放
     */
    private boolean isNeedTouchZoom = true;

    /**
     * 是否需要支持触摸缩放
     *
     * @return 返回是否需要支持触摸缩放
     */
    protected boolean isNeedTouchZoom() {
        return isNeedTouchZoom;
    }

    /**
     * 设置是否需要支持触摸缩放
     *
     * @param needTouchZoom 是否需要支持触摸缩放
     * @return {@link CameraScan}
     */
    public CameraScan setNeedTouchZoom(boolean needTouchZoom) {
        isNeedTouchZoom = needTouchZoom;
        return this;
    }

    /**
     * 设置相机配置，请在{@link #startCamera()}之前调用
     *
     * @param cameraConfig 相机配置
     * @return {@link CameraScan}
     */
    public abstract CameraScan setCameraConfig(CameraConfig cameraConfig);

    /**
     * 设置是否分析图像，通过此方法可以动态控制是否分析图像，常用于中断扫码识别。如：当扫描到结果时，请停止分析
     * 图像，处理扫描结果；如需继续连扫，等处理完后，再调用此方法，设置为true，继续扫描分析图像
     *
     * @param analyze 是否分析图像
     * @return {@link CameraScan}
     */
    public abstract CameraScan setAnalyzeImage(boolean analyze);

    /**
     * 设置分析器，如果内置的一些分析器不满足您的需求，你也可以自定义{@link Analyzer}，
     * 自定义时，切记需在{@link #startCamera()}之前调用才有效。
     *
     * @param analyzer 分析器
     * @return {@link CameraScan}
     */
    public abstract CameraScan setAnalyzer(Analyzer<T> analyzer);

    /**
     * 设置是否振动
     *
     * @param vibrate 是否振动
     * @return {@link CameraScan}
     */
    public abstract CameraScan setVibrate(boolean vibrate);

    /**
     * 设置是否播放提示音
     *
     * @param playBeep 是否播放蜂鸣提示音
     * @return {@link CameraScan}
     */
    public abstract CameraScan setPlayBeep(boolean playBeep);

    /**
     * 设置扫描结果回调
     *
     * @param callback 扫描结果回调
     * @return {@link CameraScan}
     */
    public abstract CameraScan setOnScanResultCallback(OnScanResultCallback<T> callback);

    /**
     * 绑定手电筒，绑定后可根据光线传感器，动态显示或隐藏手电筒
     *
     * @param v 手电筒视图
     * @return {@link CameraScan}
     */
    public abstract CameraScan bindFlashlightView(@Nullable View v);

    /**
     * 设置光线足够暗的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
     *
     * @param lightLux 光线亮度阈值
     * @return {@link CameraScan}
     */
    public abstract CameraScan setDarkLightLux(float lightLux);

    /**
     * 设置光线足够明亮的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
     *
     * @param lightLux 光线亮度阈值
     * @return {@link CameraScan}
     */
    public abstract CameraScan setBrightLightLux(float lightLux);

    /**
     * 扫描结果回调
     *
     * @param <T> 扫描结果数据类型
     */
    public interface OnScanResultCallback<T> {
        /**
         * 扫描结果回调
         *
         * @param result 扫描结果
         */
        void onScanResultCallback(@NonNull AnalyzeResult<T> result);

        /**
         * 扫描结果识别失败时触发此回调方法
         */
        default void onScanResultFailure() {

        }
    }

    /**
     * 解析扫描结果
     *
     * @param data 需解析的意图数据
     * @return 返回解析结果
     */
    @Nullable
    public static String parseScanResult(Intent data) {
        if (data != null) {
            return data.getStringExtra(SCAN_RESULT);
        }
        return null;
    }

}
