package com.example.myapplication

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@OptIn(ExperimentalGetImage::class)
class CameraAnalyzer : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val inputImage = InputImage.fromMediaImage(mediaImage, rotationDegrees)

            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            recognizer.process(inputImage)
                .addOnSuccessListener { visionText ->
                    processText(visionText)
                }
                .addOnFailureListener { e ->
                    println("テキスト認識エラー: ${e.message}")
                }
                .addOnCompleteListener {
                    image.close()
                }
        } else {
            image.close()
        }
    }

    private fun processText(visionText: Text) {
        println("認識されたテキスト: ${visionText.text}")
    }
}
