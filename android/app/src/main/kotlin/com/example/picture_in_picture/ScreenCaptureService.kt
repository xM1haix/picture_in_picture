package com.example.picture_in_picture

import android.app.Service
import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.IBinder
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.media.ImageReader
import android.graphics.PixelFormat
import android.view.WindowManager
import android.util.DisplayMetrics
import android.graphics.Rect
import android.media.projection.MediaProjectionManager

class ScreenCaptureService : Service() {

    private lateinit var mediaProjection: MediaProjection
    private lateinit var surfaceView: SurfaceView
    private lateinit var surface: Surface
    private var mediaProjectionManager: MediaProjectionManager? = null
    private var screenDensity: Int = 0
    private var displayMetrics: DisplayMetrics? = null

    override fun onCreate() {
        super.onCreate()

        mediaProjectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        displayMetrics = DisplayMetrics()
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenDensity = displayMetrics!!.densityDpi
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) return START_NOT_STICKY

        // Get the surface view
        surfaceView = SurfaceView(this)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                surface = holder.surface
                startCapture()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })

        return START_NOT_STICKY
    }

    private fun startCapture() {
        // Start screen capturing
        val resultCode = // Get the result code from your Intent
        val data = // Get the Intent data from your Intent
        mediaProjection = mediaProjectionManager!!.getMediaProjection(resultCode, data)
        
        // Create a Virtual Display
        val virtualDisplay = mediaProjection.createVirtualDisplay(
            "ScreenCapture",
            displayMetrics!!.widthPixels,
            displayMetrics!!.heightPixels,
            screenDensity,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            surface,
            null,
            null
        )
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
