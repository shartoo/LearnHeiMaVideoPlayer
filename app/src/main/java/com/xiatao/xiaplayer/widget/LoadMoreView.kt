package com.xiatao.xiaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.xiatao.xiaplayer.R

class LoadMoreView: RelativeLayout {
    constructor(context:Context?):super(context)
    constructor(context:Context?,attrs:AttributeSet?):super(context)
    constructor(context:Context?,attrs:AttributeSet?,defStyle:Int?):super(context)
    init{
        View.inflate(context, R.layout.view_load_more,this)
    }
}