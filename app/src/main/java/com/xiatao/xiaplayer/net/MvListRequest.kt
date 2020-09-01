package com.xiatao.xiaplayer.net

import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.model.ParseType
import com.xiatao.xiaplayer.util.URLProviderUtils
import com.xiatao.xiaplayer.widget.MvItemView

// 获取每个tab界面的详细内容：视频名称，视频url，视频背景图片url
class MvListRequest(type:Int,code:String,offset:Int,handler:ResponseHandler<ArrayList<HaoKanMV>>):MRequest<ArrayList<HaoKanMV>>(type,ParseType.SUB,URLProviderUtils.getTabUrl(code),handler) {

}