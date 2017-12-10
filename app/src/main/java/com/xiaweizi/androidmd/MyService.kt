package com.xiaweizi.androidmd

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.MyService
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/10
 *     desc   : 不同请求存放的位置
 * </pre>
 */
interface MyService {
    @get:GET("/weaponInfo/")
    val weaponInfo: Observable<WeaponModelnfo>
}