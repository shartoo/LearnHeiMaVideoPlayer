package com.xiatao.xiaplayer.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.model.MVAreaBean
import com.xiatao.xiaplayer.model.ParseType
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*
import kotlin.collections.ArrayList


class HaoKanMVUtil {


    /**
     * 抓取好看视频
     * @param html
     * :param ParseType
     * @return  String
    </Article> */
    fun spiderVideos(html: String?,type:ParseType): String {
        val document = Jsoup.parse(html)
        var listJson = ""
        if(type==ParseType.ALL){
            listJson = parseShouye(document)
        }else if(type==ParseType.SUB){
            listJson = parseTab(document)
        }
        return listJson
    }
    // 解析首页，获得每个tab的信息
    fun parseShouye(document: Document):String{
        val listJson = ArrayList<String>()
        //Log.d("Jsoup","解析首页所有tab有多少元素?${document}")
        val elements = document
                .select("nav[class=channel]")
                .select("ul[class=channel-list layout]")
                .select("li[class=channel-item float-left]")
        //Log.d("Jsoup","获得了多少个element${elements.size}")
        for (element in elements) {
            val obj = JSONObject()
            val tabUrl = element
                    .select("a")
                    .attr("href").toString()

            val tabName = element
                    .select("a").text()
            val clearntabName = tabName.replace("\\s".toRegex(),"")
            val realTabUrl = "https://haokan.baidu.com/"+URLProviderUtils.tabNameUrlMap.get(clearntabName)
            obj.put("tabUrl",realTabUrl)
            obj.put("tabName",clearntabName)
            val obj_str = obj.toString()
            listJson.add(obj_str)
        }
        val gson = Gson()
        val listObj:ArrayList<MVAreaBean> =gson.fromJson(listJson.toString(),object:TypeToken<ArrayList<MVAreaBean>>(){}.type)
        val gs = GsonBuilder().disableHtmlEscaping().create()
        return gs.toJson(listObj)
    }

    // 解析首页中的每个tab详细内容
    fun parseTab(document: Document):String{
        // 参考 https://bezkoder.com/kotlin-parse-json-gson/
        val gson = Gson()
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        val elements = document.body().text()
        val bodyRes: Map<String, Any> = gson.fromJson(elements, object : TypeToken<Map<String, Any>>() {}.type)
        val realResult = bodyRes.get("data") as LinkedTreeMap<String,Any>
        val res2 = realResult.get("response") as LinkedTreeMap<String,Any>
        val listJson = Gson().toJson(res2.get("videos"))
        val listObj:ArrayList<MVAreaBean> =gson.fromJson(listJson.toString(),object:TypeToken<ArrayList<HaoKanMV>>(){}.type)
        val gs = GsonBuilder().disableHtmlEscaping().create()
        return gs.toJson(listObj)
    }
}