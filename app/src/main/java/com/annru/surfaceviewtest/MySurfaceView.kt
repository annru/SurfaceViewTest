package com.annru.surfaceviewtest

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView : SurfaceView, SurfaceHolder.Callback,Runnable {
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

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        //Surface创建时触发
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        //Surface改变时触发
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        //Surface销毁时触发
    }

    override fun run() {

    }
}