package com.xiaweizi.androidmd

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <pre>
 * author : xiaweizi
 * class  : com.xiaweizi.androidmd.MyClient
 * e-mail : 1012126908@qq.com
 * time   : 2017/12/08
 * desc   : 用于请求网络的客户端
</pre> *
 */

class MyClient private constructor() {

    private var mBuilder: OkHttpClient.Builder? = null

    init {
        initSetting()
    }

    /**
     * 创建相应的服务接口
     */
    fun <T> create(service: Class<T>, baseUrl: String): T {
        checkNotNull(service, "service is null")
        checkNotNull(baseUrl, "baseUrl is null")

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mBuilder!!.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service)
    }

    private fun <T> checkNotNull(`object`: T?, message: String): T {
        if (`object` == null) {
            throw NullPointerException(message)
        }
        return `object`
    }

    private fun initSetting() {

        //初始化OkHttp
        mBuilder = OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)    //设置连接超时 9s
                .readTimeout(10, TimeUnit.SECONDS)      //设置读取超时 10s

        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            mBuilder!!.addInterceptor(interceptor)
        }

    }

    companion object {

        private var mMyClient: MyClient? = null

        val instance: MyClient
            get() {
                if (mMyClient == null) {
                    synchronized(MyClient::class.java) {
                        if (mMyClient == null) {
                            mMyClient = MyClient()
                        }
                    }
                }
                return mMyClient!!
            }
    }
}
