package com.xiatao.xiaplayer.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.base.BaseActivity
import com.xiatao.xiaplayer.util.FragmentUtil
import com.xiatao.xiaplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager{
    override val toolBar by lazy { find<Toolbar>(R.id.toobar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab切换监听
        // https://thesimplycoder.com/289/bottom-navigation-bar-android-using-kotlin/
        bottomNav.setOnNavigationItemSelectedListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,FragmentUtil.fragmentUtil.getFragment(it.itemId),it.toString())
            transaction.commit()
            true
        }

    }
}