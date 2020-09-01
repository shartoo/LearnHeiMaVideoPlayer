package com.xiatao.xiaplayer.presenter.impl

import android.util.Log
import com.xiatao.xiaplayer.model.MVAreaBean
import com.xiatao.xiaplayer.net.MVAreaRequest
import com.xiatao.xiaplayer.net.ResponseHandler
import com.xiatao.xiaplayer.presenter.interf.MVPresenter
import com.xiatao.xiaplayer.view.MVView

class MVPresenterImpl(var mvView:MVView?) :MVPresenter, ResponseHandler<ArrayList<MVAreaBean>> {
    // 获取所有的tab界面
    override fun loadDatas() {
        MVAreaRequest(this).execute()
    }

    override fun loadMore(offset: Int) {

    }

    override fun destoryView() {
        if(mvView!=null){
            mvView = null
        }
    }

    override fun onError(type: Int, errMsg: String?) {
        mvView?.onError(errMsg)
    }

    override fun onSuccess(type: Int, result: ArrayList<MVAreaBean>) {
        Log.d("播放器","MVPresenterImpl 载入数据成功!${result.size}")
        mvView?.loadSuccess(result)
    }
}