package com.xiatao.xiaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.model.HaoKanMV
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_mv.view.*
import kotlinx.android.synthetic.main.item_mv.view.bg
import kotlinx.android.synthetic.main.item_mv.view.title

class MvItemView:RelativeLayout{
    // 适配每个条目的view
    fun setData(data: HaoKanMV) {
        title.text = data.title
        artist.text = data.source_name
        like_count.text = data.like
        // 背景图
        val bgUrl = data.poster_big
        Picasso.get().load(bgUrl).into(bg)
    }

    constructor(context:Context?):super(context)
    constructor(context:Context,attr:AttributeSet?):super(context,attr)
    constructor(context:Context,attr:AttributeSet?,defStyleAttr:Int):super(context,attr,defStyleAttr)
    init {
        View.inflate(context, R.layout.item_mv,this)
    }
}