package com.xiatao.xiaplayer.util

import androidx.fragment.app.Fragment
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.ui.fragment.HomeFragment
import com.xiatao.xiaplayer.ui.fragment.MVFragment

/**
 * 管理Fragment的util类
 */
class FragmentUtil private  constructor(){  //私有化构造类
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MVFragment() }

    companion object{
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabId 返回对应的Fragment
     */
    fun getFragment(tabId:Int): Fragment {
        when(tabId){
            R.id.menu_home->return homeFragment
            R.id.menu_mv->return mvFragment
        }
        return homeFragment
    }
}