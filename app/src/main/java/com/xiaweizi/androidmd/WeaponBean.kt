package com.xiaweizi.androidmd

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.WeaponBean
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/08
 *     desc   :
 * </pre>
 */
data class WeaponBean(var id: Int, var name: String, var content: String, var imageUrl: String)
data class WeaponModelnfo(var status: Int, var desc: String, var data: ArrayList<WeaponBean>)