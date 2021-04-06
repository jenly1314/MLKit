package com.king.mlkit.vision.segmentation;

import androidx.annotation.Nullable;

import com.google.mlkit.vision.segmentation.SegmentationMask;
import com.king.mlkit.vision.camera.BaseCameraScanActivity;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.segmentation.analyze.SegmentationAnalyzer;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class SegmentationCameraScanActivity extends BaseCameraScanActivity<SegmentationMask> {

    @Nullable
    @Override
    public Analyzer<SegmentationMask> createAnalyzer() {
        return new SegmentationAnalyzer();
    }
}
