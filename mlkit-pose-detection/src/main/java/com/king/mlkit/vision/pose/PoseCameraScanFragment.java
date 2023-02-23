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
package com.king.mlkit.vision.pose;

import com.google.mlkit.vision.pose.Pose;
import com.king.mlkit.vision.camera.BaseCameraScanFragment;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.pose.analyze.PoseDetectionAnalyzer;

import androidx.annotation.Nullable;

/**
 * 姿势检测 - 相机扫描基类
 * <p>
 * 通过继承 {@link PoseCameraScanActivity}或{@link PoseCameraScanFragment}可快速实现姿势检测
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PoseCameraScanFragment extends BaseCameraScanFragment<Pose> {

    @Nullable
    @Override
    public Analyzer<Pose> createAnalyzer() {
        return new PoseDetectionAnalyzer();
    }
}
