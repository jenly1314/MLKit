package com.king.mlkit.vision.pose;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.pose.Pose;
import com.king.mlkit.vision.camera.BaseCameraScanActivity;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.pose.analyze.PoseDetectionAnalyzer;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PoseCameraScanActivity extends BaseCameraScanActivity<Pose> {

    @Nullable
    @Override
    public Analyzer<Pose> createAnalyzer() {
        return new PoseDetectionAnalyzer();
    }
}
