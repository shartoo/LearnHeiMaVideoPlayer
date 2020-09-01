package com.xiatao.xiaplayer.presenter.impl

import com.xiatao.xiaplayer.base.BaseListPresenter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.net.MvListRequest
import com.xiatao.xiaplayer.net.ResponseHandler
import com.xiatao.xiaplayer.presenter.interf.MvListPresenter
import com.xiatao.xiaplayer.view.MvListView
import com.xiatao.xiaplayer.widget.MvItemView

class MvListPresenterImpl(var code:String,var mvListView:MvListView?):MvListPresenter, ResponseHandler<ArrayList<HaoKanMV>> {
    override fun onError(type: Int, errMsg: String?) {
        mvListView?.onError(errMsg)
    }

    override fun onSuccess(type: Int, result: ArrayList<HaoKanMV>) {
        if(type==BaseListPresenter.TYPE_INIT_OR_REFRESH){
            mvListView?.loadSuccess(result)
        }
        else if(type==BaseListPresenter.TYPE_LOAD_MORE){
            mvListView?.loadMore(result)
        }
    }

    /**
     * 初始化或刷新操作
     */
    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,code,0,this).execute()
    }

    /**
     * 加载更多的数据
     */
    override fun loadMore(offset: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE,code,0,this).execute()
    }

    override fun destoryView() {
        if(null!=mvListView){
            mvListView=null
        }
    }

}