package com.xiatao.xiaplayer.model

import java.math.BigInteger

// 好看视频的每个tab的结构
data class HaoKanMV (
    var id:String,
    var title:String,
    var poster:String,
    var poster_small:String,
    var poster_big:String,
    var poster_pc:String,
    var source_name:String,
    var play_url:String,
    var playcnt:String,
    var mthid:BigInteger,
    var mthpic: String,
    var threadId:BigInteger,
    var site_name:String,
    var duration:String,
    var url:String,
    var cmd:String,
    var loc_id:String,
    var commentInfo:HashMap<String,String>,
    var comment_id:BigInteger,
    var show_tag:Int,
    var publish_time:String,
    var new_cate_v2:String,
    var appid:String,
    var path:String,
    var channel_name:String,
    var channel_total_number:String,
    var channel_poster:String,
    var like:String,
    var fmlike:String,
    var comment:String , //Int,
    var fmcomment:String, //Int,
    var fmplaycnt:String,
    var fmplaycnt_2:String,
    var outstand_tag:String)
{}