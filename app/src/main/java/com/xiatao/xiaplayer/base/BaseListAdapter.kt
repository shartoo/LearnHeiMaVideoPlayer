package com.xiatao.xiaplayer.base

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiatao.xiaplayer.adapter.HomeAdapter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.widget.HomeItemView
import com.xiatao.xiaplayer.widget.LoadMoreView

abstract class BaseListAdapter<T,ITEMVIEW:View>:RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {
    var list = ArrayList<T>()
    override fun onBindViewHolder(holder: BaseListAdapter.BaseListHolder, position: Int) {
        //如果是最后一条目，就不需要再刷新数据
        if(position==list.size) return
        //条目数据
        val data = list.get(position)
        //条目view
        val itemView = holder.itemView as ITEMVIEW
        // 刷新条目
        refreshView(itemView, data)
        //设置条目点击事件
        itemView.setOnClickListener {
            //条目点击事件
            listener?.let{
                // it 即其中的 listener
                it(data)
            }
        }
    }
    //kotlin 函数不需要存在于 类中，它与类同级
    // 定义函数类型变量
    var listener:((data:T)->Unit)? = null
    fun setMyListener(listener:(data:T)->Unit){
        this.listener = listener
    }
    //刷新数据
    fun updateList(dataList:ArrayList<T>?){
            this.list.clear()
            dataList?.let { it1 -> this.list.addAll(it1) }
            notifyDataSetChanged()
    }

    /**
     * 更多数据
     */
    fun loadMore(datalist:ArrayList<T>?){
        datalist?.let{
            this.list.addAll(datalist)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        if(viewType==1){
            //最后一个条目，返回更多
            return BaseListHolder(LoadMoreView(parent.context))
        }
        else{
            //普通条目
            return BaseListHolder(getItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        Log.d("手机","返回${list.size}个条目")
        return list.size +1
    }

     override fun getItemViewType(position: Int): Int {
        if(position==list.size){
            //最后一条数据
            return 1
        }else{
            //普通条目
            return 0
        }

    }

    abstract fun getItemView(context: Context?): ITEMVIEW
    //刷新条目view的抽象方法
    abstract  fun refreshView(itemView:ITEMVIEW,data:T)

    class BaseListHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}