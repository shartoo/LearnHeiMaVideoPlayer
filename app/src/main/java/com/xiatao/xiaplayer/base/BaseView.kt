package com.xiatao.xiaplayer.base

import java.util.ArrayList

/**
 * 所有由下拉刷新和上拉加载更多的界面View的基础类
 */
interface BaseView<T> {
    /**
     * 初始化数据或刷新时数据成功
     */
    fun loadSuccess(data: T?)
    /**
     * 获取数据失败
     */
    fun onError(message: String?)
    /**
     * 加载更多数据
     */
    fun loadMore(data: T?)
}