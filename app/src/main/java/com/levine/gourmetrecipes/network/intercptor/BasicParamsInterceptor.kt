package com.levine.gourmetrecipes.network.intercptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/10 16:12
 * 描    述：添加通用参数拦截器
 * 修订历史：
 */
class BasicParamsInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder().apply {
            //配置通用参数
            //addQueryParameter("access_token", "token")
        }.build()
        val request = originalRequest.newBuilder().url(url).method(originalRequest.method, originalRequest.body).build()
        return chain.proceed(request)
    }
}