package com.xiatao.xiaplayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * 所有Fragment的基类
 */
abstract class BaseFragment:Fragment(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()

    }
    /**
     * 数据的初始化
     */
    open fun initData(){

    }
    /**
     * adapter 以及 listener的相关操作
     */
    open fun initListener(){ }

    /**
     * 获取布局View
     */
    abstract fun initView():View?
    /**
     * fragment的初始化
     */
    open fun init(){

    }
    fun myToast(msg:String){
        requireContext().runOnUiThread {toast(msg) }
    }
}