package com.example.picture_in_picture

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example/screen_capture"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "startCapture" -> {
                    startScreenCapture()
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun startScreenCapture() {
        val intent = Intent(this, ScreenCaptureService::class.java)
        startService(intent)
    }
}
