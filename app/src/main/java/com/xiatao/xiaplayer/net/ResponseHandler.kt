package com.xiatao.xiaplayer.net

/**
 * 使用volley的请求回调
 */
interface ResponseHandler<RESPONSE> {
     fun onError(type:Int,errMsg:String?)
     fun onSuccess(type:Int,result:RESPONSE)
}