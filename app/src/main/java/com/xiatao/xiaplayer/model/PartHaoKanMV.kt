package com.xiatao.xiaplayer.model

import android.os.Parcel
import android.os.Parcelable

// HaoKanMV bean的部分字段，方便后面传给 视频播放的 activity,因为没必要传HaoKanMV的所有字段。同时需要对Bean类实现序列化接口
data class PartHaoKanMV(var id:String?,var title:String?,var videoUrl:String?,var comment:String?,var like:Int):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(videoUrl)
        parcel.writeString(comment)
        parcel.writeInt(like)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PartHaoKanMV> {
        override fun createFromParcel(parcel: Parcel): PartHaoKanMV {
            return PartHaoKanMV(parcel)
        }

        override fun newArray(size: Int): Array<PartHaoKanMV?> {
            return arrayOfNulls(size)
        }
    }
}