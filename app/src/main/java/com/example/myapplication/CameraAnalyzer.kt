package com.example.myapplication
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.core.ImageProxy

class CameraAnalyzer {
    fun ImageProxy.toBitmap(): Bitmap? {
        val buffer = this.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
// ここに analyze() を作成して ML Kit 連携などを進める
}