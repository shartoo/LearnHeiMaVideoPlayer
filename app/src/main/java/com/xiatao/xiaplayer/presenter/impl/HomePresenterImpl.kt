package com.xiatao.xiaplayer.presenter.impl

import com.xiatao.xiaplayer.base.BaseListPresenter
import com.xiatao.xiaplayer.base.BaseView
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.net.HomeRequest
import com.xiatao.xiaplayer.net.NetManager
import com.xiatao.xiaplayer.net.ResponseHandler
import com.xiatao.xiaplayer.presenter.interf.HomePresenter
import com.xiatao.xiaplayer.util.HaoKanMVUtil
import com.xiatao.xiaplayer.util.ThreadUtil
import com.xiatao.xiaplayer.util.URLProviderUtils
import com.xiatao.xiaplayer.view.HomeView
import okhttp3.*
import java.io.IOException

class HomePresenterImpl(var homeView: BaseView<ArrayList<HaoKanMV>>?):HomePresenter, ResponseHandler<ArrayList<HaoKanMV>> {
    //解绑 View 和 Presenter
    override  fun destoryView(){
        if(homeView!=null){
            homeView = null
        }
    }
    //加载数据失败
    override fun onError(type:Int,errMsg: String?) {
            homeView?.onError(errMsg)
    }
    // 加载数据成功
    override fun onSuccess(type:Int,result: ArrayList<HaoKanMV>) {
        when(type){
            BaseListPresenter.TYPE_INIT_OR_REFRESH->homeView?.loadSuccess(result)
            BaseListPresenter.TYPE_LOAD_MORE->homeView?.loadMore(result)
        }
    }

    override fun loadMore(offset:Int) {
        // 只需要两步
        // 1. 定义MRequest                                             // 发送请求
        HomeRequest(BaseListPresenter.TYPE_LOAD_MORE,offset,this).execute()
    }

    override fun loadDatas() {
        // 只需要两步
        // 1. 定义MRequest
       HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,0,this).execute()

    }

}