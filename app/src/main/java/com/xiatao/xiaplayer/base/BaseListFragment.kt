package com.xiatao.xiaplayer.base

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.adapter.HomeAdapter
import com.xiatao.xiaplayer.base.BaseFragment
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.presenter.impl.HomePresenterImpl
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 所有具有以下功能的基类
 *  1. 下拉刷新
 *  2. 上拉加载更多
 *  需要处理的
 *  View
 *  Adapter
 *  Presenter
 */
//                            此处的泛型  M  就是 ArrayList<T> 类型
abstract  class BaseListFragment<M,T,ITEAMVIEW:View>:BaseFragment(),BaseView<M> {
    val adapter by lazy {getSpecAdapter()}

    val resenter by lazy { getSpecPresenter() }

    override fun loadSuccess(data: M?) {
        //隐藏刷新控件
       // swipe_container.isRefreshing = false
        //刷新列表
        adapter.updateList(getLocalData(data))
    }

    override fun loadMore(data: M?) {
        adapter.loadMore(getLocalData(data))
    }

     override  fun onError(message: String?) {
        myToast("加载数据失败$message")
    }

    override fun initListener(){
        //初始化recycle_view
        recycler_view.layoutManager = LinearLayoutManager(context)
        //适配
        recycler_view.adapter =adapter
        //初始化刷新控件
        swipe_container.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE)
        swipe_container.setOnRefreshListener{
            resenter.loadDatas()
        }
        //监听列表滑动
        recycler_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    //是否最后一条已经显示
                    val layMag = recyclerView.layoutManager
                    if(layMag is LinearLayoutManager){
                        val manger: LinearLayoutManager = layMag
                        val lastPos = manger.findLastVisibleItemPosition()
                        if(lastPos == adapter.itemCount-1){
                            //最后一条已经显示了
                            resenter.loadMore(adapter.itemCount-1)
                        }
                    }
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })
    }
    override fun initData(){
        //初始化数据
        resenter.loadDatas()
    }
    // 获取适配器 adapter
    abstract  fun getSpecAdapter():BaseListAdapter<T,ITEAMVIEW>
    //
    abstract fun getSpecPresenter():BaseListPresenter
    // 从返回结果中获取列表数据集合
    abstract  fun getLocalData(data:M?):ArrayList<T>?

}