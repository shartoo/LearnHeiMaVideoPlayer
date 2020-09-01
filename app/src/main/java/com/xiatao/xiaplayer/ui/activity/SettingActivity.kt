package com.xiatao.xiaplayer.ui.activity

import android.util.Log
import android.widget.Toolbar
import androidx.preference.PreferenceManager
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.base.BaseActivity
import com.xiatao.xiaplayer.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * 设置布局
 */
class SettingActivity:BaseActivity(),ToolBarManager {
    override val toolBar by lazy {
        Log.d("设置","toolBar已经初始化了")
        findViewById<Toolbar>(R.id.toobar)
//        find<Toolbar>(R.layout.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolBar()
        //获取推送通知是否有被选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val if_push= sp.getBoolean("push_notifaction",false)
        Log.d("Fragment","是否有被选择 打开通知$if_push")
    }
}