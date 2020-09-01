package com.xiatao.xiaplayer.base

/**
 * 所有下拉刷新和上拉加载更多界面的Presenter的基础类
 */
interface BaseListPresenter {
    companion object{
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }
    /**
     * 初始化或刷新操作
     */
    fun loadDatas()
    /**
     * 加载更多的数据
     */
    fun loadMore(offset:Int)
    // 解绑 presenter 和 view
    fun destoryView()
}