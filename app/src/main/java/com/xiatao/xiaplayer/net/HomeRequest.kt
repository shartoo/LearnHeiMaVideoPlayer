package com.xiatao.xiaplayer.net

import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.model.ParseType
import com.xiatao.xiaplayer.util.URLProviderUtils

class HomeRequest(type:Int,offset:Int,hander: ResponseHandler<ArrayList<HaoKanMV>>):
        MRequest<ArrayList<HaoKanMV>>(type,ParseType.ALL,URLProviderUtils.getHomeUrl(),hander) {

}