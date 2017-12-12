package com.xiaweizi.androidmd

import android.os.Parcel
import android.os.Parcelable

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.WeaponBean
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/08
 *     desc   : 获取武器信息的 bean 和 modelInfo
 * </pre>
 */
data class WeaponBean(var id: Int, var name: String, var content: String, var imageUrl: String, var symbol: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(content)
        parcel.writeString(imageUrl)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeaponBean> {
        override fun createFromParcel(parcel: Parcel): WeaponBean {
            return WeaponBean(parcel)
        }

        override fun newArray(size: Int): Array<WeaponBean?> {
            return arrayOfNulls(size)
        }
    }
}

data class WeaponModelnfo(var status: Int, var desc: String, var data: ArrayList<WeaponBean>)