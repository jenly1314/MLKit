package com.king.mlkit.vision.segmentation.analyze;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.segmentation.Segmentation;
import com.google.mlkit.vision.segmentation.SegmentationMask;
import com.google.mlkit.vision.segmentation.Segmenter;
import com.google.mlkit.vision.segmentation.selfie.SelfieSegmenterOptions;
import com.king.mlkit.vision.camera.AnalyzeResult;
import com.king.mlkit.vision.camera.analyze.Analyzer;
import com.king.mlkit.vision.camera.util.ImageUtils;
import com.king.mlkit.vision.camera.util.LogUtils;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class SegmentationAnalyzer implements Analyzer<SegmentationMask> {

    private Segmenter mDetector;

    public SegmentationAnalyzer(){
        this(null);
    }

    public SegmentationAnalyzer(SelfieSegmenterOptions options){
        if(options != null){
            mDetector = Segmentation.getClient(options);
        }else{
            mDetector = Segmentation.getClient(new SelfieSegmenterOptions.Builder()
                    .setDetectorMode(SelfieSegmenterOptions.STREAM_MODE)
                    .enableRawSizeMask()
                    .build());
        }
    }

    @Override
    public void analyze(@NonNull ImageProxy imageProxy, @NonNull OnAnalyzeListener<AnalyzeResult<SegmentationMask>> listener) {
        try {

            final Bitmap bitmap = ImageUtils.imageProxyToBitmap(imageProxy,imageProxy.getImageInfo().getRotationDegrees());
//            @SuppressLint("UnsafeExperimentalUsageError")
//            InputImage inputImage = InputImage.fromMediaImage(imageProxy.getImage(),imageProxy.getImageInfo().getRotationDegrees());
            InputImage inputImage = InputImage.fromBitmap(bitmap,0);

            mDetector.process(inputImage).addOnSuccessListener(result -> {
                if(result != null){
                    listener.onSuccess(new AnalyzeResult<>(bitmap,result));
                }else {
                    listener.onFailure();
                }
            }).addOnFailureListener(e -> {
                listener.onFailure();
            });
        } catch (Exception e) {
            LogUtils.w(e);
        }
    }
}
