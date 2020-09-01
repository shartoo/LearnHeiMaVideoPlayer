package com.xiatao.xiaplayer.net

import com.xiatao.xiaplayer.model.MVAreaBean
import com.xiatao.xiaplayer.model.ParseType
import com.xiatao.xiaplayer.util.URLProviderUtils

// 获取所有的tab界面
class MVAreaRequest(handler:ResponseHandler<ArrayList<MVAreaBean>>):MRequest<ArrayList<MVAreaBean>>(0,ParseType.ALL,URLProviderUtils.getHomeUrl(),handler){

}