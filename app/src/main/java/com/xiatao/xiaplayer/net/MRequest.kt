package com.xiatao.xiaplayer.net

import com.google.gson.Gson
import com.xiatao.xiaplayer.model.ParseType
import com.xiatao.xiaplayer.util.HaoKanMVUtil
import org.json.JSONObject
import java.lang.reflect.Parameter
import java.lang.reflect.ParameterizedType
import java.net.URLEncoder

/**
 * 所有请求的基类
 */
open class MRequest<RESPONSE>(val type:Int,val parseType:ParseType,val url:String,val handler:ResponseHandler<RESPONSE>) {
    /**
     * 解析网络请求的结果
     */
    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        // 获取泛型类型
        val classType = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val haokanUtil: HaoKanMVUtil = HaoKanMVUtil()
        val result = haokanUtil.spiderVideos(result,parseType)
        //println("result in MRequest${result}")
        val list = gson.fromJson<RESPONSE>(result,classType) // URLEncoder.encode(result,"utf8")
        return list
    }
    fun execute(){
        NetManager.manager.sendRequest(this)
    }
}