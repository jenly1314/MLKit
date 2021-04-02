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
package com.king.mlkit.vision.app

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.king.mlkit.vision.camera.util.LogUtils

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
fun Bitmap.drawBitmap(block: (canvas: Canvas,paint: Paint) -> Unit): Bitmap {
    var result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    try {
        val canvas = Canvas(result)
        canvas.drawBitmap(this, 0f, 0f, null)
        val paint = Paint()
        paint.strokeWidth = 4f
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.RED

        block(canvas,paint)

        canvas.save()
        canvas.restore()
    } catch (e: Exception) {
        LogUtils.w(e.message)
    }
    return result
}

fun Bitmap.drawRect(block: (canvas: Canvas,paint: Paint) -> Unit): Bitmap {
    var result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    try {
        val canvas = Canvas(result)
        canvas.drawBitmap(this, 0f, 0f, null)
        val paint = Paint()
        paint.strokeWidth = 6f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED

        block(canvas,paint)

        canvas.save()
        canvas.restore()
    } catch (e: Exception) {
        LogUtils.w(e.message)
    }
    return result
}

