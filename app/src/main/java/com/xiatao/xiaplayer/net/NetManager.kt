package com.xiatao.xiaplayer.net

import com.xiatao.xiaplayer.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * 发送网络请求类
 */
class NetManager private  constructor(){

    val client by lazy { OkHttpClient()}
    // 单例 模式
    companion object{
        val manager by lazy { NetManager()}
    }

    /**
     * 发送请求
     */
    fun <RESPONSE>sendRequest(req:MRequest<RESPONSE>){
        val request = Request.Builder()
                .url(req.url)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 在子线程中调用
             */
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据失败"+Thread.currentThread().name)
                ThreadUtil.runOnMainThread(object :Runnable{
                    override  fun run(){
                        req.handler.onError(req.type,e?.message)
                    }
                })
            }
            /**
             * 在子线程中调用
             */
            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //adapter.loadMore(videos)
                        // 将结果回调到view层
                        //homeView.loadMore(videos)
                        req.handler.onSuccess(req.type,parseResult)
                    }
                })
            }
        })
    }
}