package com.xiatao.xiaplayer.util

import android.os.Looper
import java.util.logging.Handler

object  ThreadUtil {
    val handler = android.os.Handler(Looper.getMainLooper())
    //运行在主线程中
    fun runOnMainThread(runnable: Runnable){
            handler.post(runnable)
    }
}