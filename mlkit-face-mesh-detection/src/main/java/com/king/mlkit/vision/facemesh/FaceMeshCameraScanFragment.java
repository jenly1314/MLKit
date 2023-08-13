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
package com.king.mlkit.vision.facemesh;

import com.google.mlkit.vision.facemesh.FaceMesh;
import com.king.camera.scan.BaseCameraScanFragment;
import com.king.camera.scan.analyze.Analyzer;
import com.king.mlkit.vision.facemesh.analyze.FaceMeshDetectionAnalyzer;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 人脸网格检测 - 相机扫描基类
 * <p>
 * 通过继承 {@link FaceMeshCameraScanActivity}或{@link FaceMeshCameraScanFragment}可快速实现人脸网格检测
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class FaceMeshCameraScanFragment extends BaseCameraScanFragment<List<FaceMesh>> {

    @Nullable
    @Override
    public Analyzer<List<FaceMesh>> createAnalyzer() {
        return new FaceMeshDetectionAnalyzer();
    }

}
