package com.levine.gourmetrecipes.network

import com.levine.gourmetrecipes.BuildConfig
import com.levine.gourmetrecipes.network.intercptor.BasicParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/9 17:26
 * 描    述：
 * 修订历史：
 */
object NetworkService {
    private var okhttpClient: OkHttpClient

    private var retrofit: Retrofit

    init {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.addInterceptor(BasicParamsInterceptor())
        if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okhttpClientBuilder.addInterceptor(logInterceptor)
        }
        okhttpClient = okhttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(okhttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}