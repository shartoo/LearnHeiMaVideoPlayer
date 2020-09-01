package com.xiatao.xiaplayer.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity:BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
       //动画结束后 进入主界面
        startActivityAndFinish<MainActivity>()
        Log.d ("SplashActivity","动画结束，进入主页面" )
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this
        ).setDuration(2000)
    }
}