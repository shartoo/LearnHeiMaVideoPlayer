package com.xiatao.xiaplayer.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 所有Activity的基类
 */
abstract  class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        Log.d("BaseActivity","进入到onCreate方法中..")
        initListener()
        initData()
    }
    /**
     * 初始化数组操作
     */
    open protected fun initData() {
    }

    /**
     * adapater 以及 listener的相关操作
     */
    open protected fun initListener() {

    }

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId():Int

    protected fun myToast(msg:String){
            runOnUiThread(){
                toast(msg)
            }
    }

    /***
     *  开启activity并且结束当前界面
     */
    inline fun <reified T: BaseActivity>startActivityAndFinish(){
        startActivity<T>()
        finish()
    }
}