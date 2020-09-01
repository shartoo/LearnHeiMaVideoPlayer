package com.xiatao.xiaplayer.ui.fragment

import android.content.Intent
import android.view.View
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.adapter.MvListAdapter
import com.xiatao.xiaplayer.base.BaseListAdapter
import com.xiatao.xiaplayer.base.BaseListFragment
import com.xiatao.xiaplayer.base.BaseListPresenter
import com.xiatao.xiaplayer.model.HaoKanMV
import com.xiatao.xiaplayer.model.PartHaoKanMV
import com.xiatao.xiaplayer.presenter.impl.MvListPresenterImpl
import com.xiatao.xiaplayer.ui.activity.ExoVideoPlayActivity
import com.xiatao.xiaplayer.view.MvListView
import com.xiatao.xiaplayer.widget.MvItemView

/**
 * mv 界面的每一个page界面
 */
class MvPagerFragment: BaseListFragment<ArrayList<HaoKanMV>,HaoKanMV,MvItemView>(), MvListView {
    var name:String? = null
    override fun init() {
        name = arguments?.getString("args")
    }
    override fun getSpecAdapter(): BaseListAdapter<HaoKanMV, MvItemView> {
        return MvListAdapter()
    }

    override fun getSpecPresenter(): BaseListPresenter {
        return MvListPresenterImpl(name!!,this)
    }

    override fun getLocalData(data:ArrayList<HaoKanMV>?): ArrayList<HaoKanMV>? {
        return data
    }

    /**
     * 获取布局View
     */
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        super.initListener()

        // 设置 条目点击事件的监听,此处的it就是data。 Kotlin通过函数的方式将点击事件传到主界面
        adapter.setMyListener {
            val videoBean = PartHaoKanMV(it.id,it.title,it.play_url,it.comment,it.like.toFloat().toInt())
            val intent = Intent(context, ExoVideoPlayActivity::class.java).apply {
                putExtra("item" , videoBean)
            }
            startActivity(intent)
        }
    }

}