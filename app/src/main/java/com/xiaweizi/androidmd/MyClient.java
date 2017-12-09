package com.xiaweizi.androidmd;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.MyClient
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/08
 *     desc   :
 * </pre>
 */

public class MyClient {

    private static MyClient mMyClient;

    private OkHttpClient.Builder mBuilder;

    private MyClient() {
        initSetting();
    }

    public static MyClient getInstance() {
        if (mMyClient == null) {
            synchronized (MyClient.class) {
                if (mMyClient == null) {
                    mMyClient = new MyClient();
                }
            }
        }
        return mMyClient;
    }

    /**
     * 创建相应的服务接口
     */
    public <T> T create(Class<T> service, String baseUrl) {
        checkNotNull(service, "service is null");
        checkNotNull(baseUrl, "baseUrl is null");

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service);
    }

    private <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    private void initSetting() {

        //初始化OkHttp
        mBuilder = new OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)    //设置连接超时 9s
                .readTimeout(10, TimeUnit.SECONDS);      //设置读取超时 10s

        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mBuilder.addInterceptor(interceptor);
        }

    }
}
