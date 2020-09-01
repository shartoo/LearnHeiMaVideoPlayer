package com.xiatao.xiaplayer.widget

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.model.HaoKanMV
import kotlinx.android.synthetic.main.item_home.view.*
import java.util.jar.Attributes

class HomeItemView:RelativeLayout {
    /**
     * 刷新数据条目
     */
    fun setData(data: HaoKanMV) {
        //歌曲名称
        title.setText(data.title)
        //视频
        //背景图片，暂时没有
        val imgUrl = data.poster_big
        Picasso.get().load(imgUrl).into(bg)
        //描述
        desc.setText("歌手描述")
    }

    // 一些次构造方法，后面的super(context)才是主构造方法
    constructor(context: Context?):super(context)
    constructor(context:Context?,attrs:Attributes):super(context)
    constructor(context: Context?,attrs: Attributes,defStyleAttr:Int):super(context)
    //初始化方法,无论主构造方法还是次构造方法都会访问 init方法
    //init里面只能访问次构造方法里面的参数，如果要访问主构造方法里面的参数，需要再init之外 用其他变量引用才行
    init{
        View.inflate(context, R.layout.item_home,this)
    }
}