package com.xiatao.xiaplayer.util

import android.util.Log
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import java.time.Instant

class URLProviderUtils {

    companion object{
        val tabNameUrlMap: HashMap<String, String> = hashMapOf("综艺" to "tab/zongyi",
                "推荐" to "tab/tuijian",
                "影视" to "tab/yingshi",
                "音乐" to "tab/yinyue",
                "VLOG" to "tab/vlog",
                "游戏" to "tab/youxi",
                "搞笑" to "tab/gaoxiao",
                "娱乐" to "tab/yule",
                "动漫" to "tab/dongman",
                "生活" to "tab/shenghuo",
                "广场舞" to "tab/guangchangwu",
                "美食" to "tab/meishi",
                "宠物" to "tab/chongwu",
                "三农" to "tab/sannong",
                "军事" to "tab/junshi",
                "社会" to  "tab/shehui"
        )
        val client = OkHttpClient()
        /**
         * 获取首页的url
         *
         * @param offset 数据偏移量
         * @param size   返回数据的条目个数
         * @return url
         */
        fun getHomeUrl(): String {
            val url = "https://haokan.baidu.com"
            return url
        }
        // 获取所有tab的界面，从此界面中 找到各个tab
        fun getTabUrl(tab:String):String{
            val tabName = tabNameUrlMap.get(tab)
            val time = System.currentTimeMillis()
            val realUrl = "https://haokan.baidu.com/videoui/api/videorec?tab=${tabName}&act=pcFeed&pd=pc&num=5&shuaxin_id=${time}"
            return realUrl
        }

        fun getMVListUrl(code:String,offset: Int, size: Int): String {
            return "https://haokan.baidu.com/tab/"+code
        }
    }
}