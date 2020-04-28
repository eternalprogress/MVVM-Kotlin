package com.joker.mvvm.kotlin.base

import com.joker.mvvm.kotlin.basemvvm.net.BaseretrofitClient
import com.joker.mvvm.kotlin.net.service.ApiService

/**
 * @Author joker
 * @Date 2020-04-28-11:33
 */
object MyRetrofitManager:BaseretrofitClient() {
    private const val baseUrl = "https://www.fastmock.site/mock/4dadfc73c3e20267223eebca11d3632b/study/"
    val service by lazy {
        getService(ApiService::class.java,baseUrl)
    }

    //如果你想修改超时时间重写此方法 不修改则使用默认5s
    override fun setConnectTimeout(): Int  = 7

    //如果你想修改HeaderInterceptor重写此方法 不重写使用框架默认类 HeaderInterceptor
    /*override fun setHeaderInterceptor(): Interceptor? {
        return super.setHeaderInterceptor()
    }*/



    //如果你想修改HttpLoggingInterceptor重写此方法 不重写使用框架默认类 HttpLogInterceptor
    //此方法内有推荐写法 这样可以只在debug包的时候才会打日志
    /*override fun setHttpLoggingInterceptor(): Interceptor? =
        if (BuildConfig.DEBUG) {
            //你自己的日志实现类 例如HttpLogInterceptor
            HttpLogInterceptor()
        }else {
             super.setHttpLoggingInterceptor()
        }*/


    //如果你想修改OkHttpClient重写此方法 不重写使用框架默认OkHttpClient
    /*override fun setOkhttpClient(): OkHttpClient? {
        return super.setOkhttpClient()
    }*/



}