package com.annru.surfaceviewtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView : SurfaceView, SurfaceHolder.Callback, Runnable {
    private val tag = javaClass.simpleName

    private lateinit var surfaceHolder: SurfaceHolder
    private var canvas: Canvas? = null
    private lateinit var paint: Paint
    private lateinit var bgPaint: Paint
    private var progress = 0

    private var bDrawing = false

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        init()
    }

    private fun init() {
        paint = Paint()
        with(paint) {
            color = Color.RED
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 20f
        }

        //背景
        // 初始化背景
        bgPaint = Paint()
        with(bgPaint) {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            color = Color.GRAY
            strokeWidth = 20f
        }

        this.surfaceHolder = holder
        //注册回调
        this.surfaceHolder.addCallback(this)

        //画布透明处理
        this.setZOrderOnTop(true)
        this.surfaceHolder.setFormat(PixelFormat.TRANSLUCENT)

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.e(tag, "surfaceCreated")
        bDrawing = true
        //Surface创建时触发
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.e(tag, "surfaceChanged")
        //Surface改变时触发
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.e(tag, "surfaceDestroyed")
        //Surface销毁时触发
        bDrawing = false
    }

    override fun run() {
        while (bDrawing) {
            drawing()
            try {
                Thread.sleep(100)
                progress++
                if (progress >= 100) {
                    progress = 0
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

    }

    private fun drawing() {
        canvas = this.surfaceHolder.lockCanvas()
        canvas?.let {
            try {
                synchronized(surfaceHolder) {
                    canvas!!.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                    val xCenter = width / 2
                    val yCenter = height / 2
                    val radius = xCenter - 10
                    val rectF = RectF(
                        (xCenter - radius).toFloat(),
                        (yCenter - radius).toFloat(),
                        (xCenter + radius).toFloat(),
                        (yCenter + radius).toFloat()
                    )
                    canvas!!.drawArc(rectF, -90F, (progress * 360 / 100).toFloat(), false, paint)
                }
            } finally {
                this.surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}