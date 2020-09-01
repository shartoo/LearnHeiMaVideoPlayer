package com.xiatao.xiaplayer.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.xiatao.xiaplayer.model.MVAreaBean
import com.xiatao.xiaplayer.ui.fragment.MvPagerFragment

// mv界面的每个tab界面
class MVPageAdapter(val list:ArrayList<MVAreaBean>?, fg:Fragment ) : FragmentStateAdapter(fg){

    override fun createFragment(position: Int): Fragment {
        // 第一种数据传递的方法
        val fragment = MvPagerFragment()
        // 使用bundle 的方式给 Fragment传值，而不是使用构造方法中传值
        val bundle = Bundle()
        bundle.putString("args",list?.get(position)?.tabName)
        fragment.arguments = bundle
        // 第二种数据传递的方式
        //val fragment2= Fragment.instantiate(context,MvPagerFragment::class.java.name,bundle)
        return fragment
    }
    // MV界面 区域数据MVAreaBean 数量确定
    override fun getItemCount(): Int {
        var size = 0
        if(null!=list){
            size = list.size
        }
        return size
    }

}