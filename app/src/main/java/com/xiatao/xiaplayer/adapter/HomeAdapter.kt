package com.xiatao.xiaplayer.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.xiatao.xiaplayer.base.BaseListAdapter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.widget.HomeItemView
import com.xiatao.xiaplayer.widget.LoadMoreView
import org.jsoup.Connection

class HomeAdapter:BaseListAdapter<HaoKanMV,HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        //普通条目
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: HaoKanMV) {
            itemView.setData(data)
    }


}