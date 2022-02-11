package com.levine.gourmetrecipes.network.intercptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/10 16:23
 * 描    述：自定义header拦截器
 * 修订历史：
 */
class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().apply {
            //自定义header
            //header("custom_header", "header")
        }.build()
        return chain.proceed(request)
    }
}