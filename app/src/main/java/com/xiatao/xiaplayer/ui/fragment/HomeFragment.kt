package com.xiatao.xiaplayer.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiatao.xiaplayer.adapter.HomeAdapter
import com.xiatao.xiaplayer.base.BaseListAdapter
import com.xiatao.xiaplayer.base.BaseListFragment
import com.xiatao.xiaplayer.base.BaseListPresenter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.presenter.impl.HomePresenterImpl
import com.xiatao.xiaplayer.presenter.interf.HomePresenter
import com.xiatao.xiaplayer.util.HaoKanMVUtil
import com.xiatao.xiaplayer.util.ThreadUtil
import com.xiatao.xiaplayer.util.URLProviderUtils
import com.xiatao.xiaplayer.view.HomeView
import com.xiatao.xiaplayer.widget.HomeItemView
import okhttp3.*
import java.io.IOException
import java.util.ArrayList

class HomeFragment:BaseListFragment<ArrayList<HaoKanMV>,HaoKanMV,HomeItemView>(){
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_home,null)
    }

    override fun getSpecAdapter(): BaseListAdapter<HaoKanMV, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getLocalData(data: ArrayList<HaoKanMV>?): ArrayList<HaoKanMV>? {
        return data
    }
    override fun onDestroy() {
        super.onDestroy()

    }
}