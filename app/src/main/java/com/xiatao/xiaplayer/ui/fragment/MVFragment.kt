package com.xiatao.xiaplayer.ui.fragment

import android.util.Log
import android.view.*
import com.google.android.material.tabs.TabLayoutMediator
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.adapter.MVPageAdapter
import com.xiatao.xiaplayer.base.BaseFragment
import com.xiatao.xiaplayer.model.MVAreaBean
import com.xiatao.xiaplayer.presenter.impl.MVPresenterImpl
import com.xiatao.xiaplayer.view.MVView
import kotlinx.android.synthetic.main.fragment_mv.*

class MVFragment:BaseFragment(), MVView {
    val presenter by lazy { MVPresenterImpl(this) }
    override fun loadSuccess(data: ArrayList<MVAreaBean>?) {
        Log.d("播放器","载入MV数据成功 ${data?.size}")
        val adapter = MVPageAdapter(data,this)
        viewPager.adapter = adapter
        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            viewPager.setCurrentItem(position,true)
            val name = adapter.list?.get(position)?.tabName
            Log.d("播放器","当前tab名称为${name}")
            tab.setText(name)
        }.attach()
    }

    override fun loadMore(data: ArrayList<MVAreaBean>?) {

    }

    override fun onError(errMsg: String?) {
        myToast("载入MV信息失败!")
    }

    override fun initView(): View? {
        Log.d("播放器","当前是MVFragmet")
        return View.inflate(context, R.layout.fragment_mv,null)

    }

    override fun initListener() {

    }

    override fun initData() {
    // 加载区域数据
        presenter.loadDatas()
    }
}