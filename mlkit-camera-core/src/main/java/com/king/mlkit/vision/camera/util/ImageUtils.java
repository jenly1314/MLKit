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
package com.king.mlkit.vision.camera.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;

import androidx.camera.core.ImageProxy;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class ImageUtils {

    private ImageUtils(){
        throw new AssertionError();
    }

    public static Bitmap imageProxyToBitmap(ImageProxy imageProxy) throws Exception{
        return imageProxyToBitmap(imageProxy, imageProxy.getImageInfo().getRotationDegrees());
    }

    public static Bitmap imageProxyToBitmap(ImageProxy imageProxy,int rotationDegrees) throws Exception{
        ImageProxy.PlaneProxy[] plane = imageProxy.getPlanes();
        ByteBuffer yBuffer = plane[0].getBuffer();  // Y
        ByteBuffer uBuffer = plane[1].getBuffer();  // U
        ByteBuffer vBuffer = plane[2].getBuffer();  // V

        int ySize = yBuffer.remaining();
        int uSize = uBuffer.remaining();
        int vSize = vBuffer.remaining();

        byte[] nv21 = new byte[ySize + uSize + vSize];

        //U and V are swapped
        yBuffer.get(nv21, 0, ySize);
        vBuffer.get(nv21, ySize, vSize);
        uBuffer.get(nv21, ySize + vSize, uSize);

        YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, imageProxy.getWidth(), imageProxy.getHeight(), null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream(nv21.length);
        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 90, stream);

        Bitmap bitmap = BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size());
        if(rotationDegrees != 0){
            Matrix matrix = new Matrix();
            matrix.postRotate(rotationDegrees);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(), matrix, true);
        }

        return bitmap;
    }
}
