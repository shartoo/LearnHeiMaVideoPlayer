package com.xiatao.xiaplayer.widget

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.model.HaoKanMV
import kotlinx.android.synthetic.main.item_vlist.view.*
import java.util.jar.Attributes

/**
 * 悦单界面每个条目的自定义View
 */
class VlistItemView: RelativeLayout {
    // 数据条目 View 的初始化
    fun setDatas(data: HaoKanMV) {
        // 歌单名称
        title.text = "歌单名称"
        // 歌手名称
        author_name.text = data.title
        //歌曲数目
        count.text = "歌单数量:"+20.toString()
        // 背景图
        val bgUrl = "https://c-ssl.duitang.com/uploads/item/202007/15/20200715131146_wuycu.thumb.400_0.png"
        Picasso.get().load(bgUrl).into(bg)
        // 头像
        val authorBgUrl = "https://c-ssl.duitang.com/uploads/item/202007/18/20200718185227_HtSdZ.thumb.400_0.jpeg"
        Picasso.get().load(authorBgUrl).into(bg)
    }

    // 一些次构造方法，后面的super(context)才是主构造方法
    constructor(context: Context?):super(context)
    constructor(context: Context?, attrs: Attributes):super(context)
    constructor(context: Context?, attrs: Attributes, defStyleAttr:Int):super(context)
    init{
        View.inflate(context, R.layout.item_vlist,this)
    }

}