package com.xiatao.xiaplayer.adapter

import android.content.Context
import com.xiatao.xiaplayer.base.BaseListAdapter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.widget.MvItemView

// mv界面每个list列表的适配器
class MvListAdapter:BaseListAdapter<HaoKanMV,MvItemView>() {
    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }

    override fun refreshView(itemView: MvItemView, data: HaoKanMV) {
        itemView.setData(data)
    }
}