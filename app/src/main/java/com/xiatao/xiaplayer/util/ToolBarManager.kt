package com.xiatao.xiaplayer.util

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.ui.activity.SettingActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.startActivity

/**
 * 所有ToolBar的管理类
 */
interface ToolBarManager {
    val toolBar: Toolbar
    /**
     * 初始化MainActivity中的toolbar
     */
    fun initMainToolBar() {
        Log.d("ToolBar", "执行了initMainToolBar")
        toolBar.inflateMenu(R.menu.main)
        toolBar.setOnMenuItemClickListener {
            toolBar.context.startActivity(Intent(toolBar.context,SettingActivity::class.java))
            true
        }
//        toolBar.setOnMenuItemClickListener(@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        object : Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.setting -> {
//                        toolBar.context.startActivity(Intent(toolBar.context,SettingActivity::class.java))
//                    }
//                }
//                return true
//            }
//        })
        /**
         * 处理设置界面的toolbar
         */

    }
    fun initSettingToolBar(){
        toolBar.setTitle("设置界面")
    }
}