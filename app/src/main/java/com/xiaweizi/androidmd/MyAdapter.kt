package com.xiaweizi.androidmd

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.MyAdapter
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/08
 *     desc   :
 * </pre>
 */
class MyAdapter(layoutResId: Int) : BaseQuickAdapter<WeaponBean, BaseViewHolder>(layoutResId) {

    constructor() : this(layoutResId = R.layout.item_weapon_info)

    override fun convert(helper: BaseViewHolder?, item: WeaponBean?) {
        Glide.with(mContext).load(item?.imageUrl).into(helper!!.getView(R.id.iv_weapon))
        helper.setText(R.id.tv_weapon_name, item!!.name)
    }

}