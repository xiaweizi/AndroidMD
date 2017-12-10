package com.xiaweizi.androidmd

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.MyService
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/10
 *     desc   :
 * </pre>
 */
interface MyService {
    @get:GET("/weaponInfo/")
    val weaponInfo: Observable<WeaponModelnfo>
}