package com.levine.gourmetrecipes

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import timber.log.Timber

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/8 16:17
 * 描    述：
 * 修订历史：
 */
class GourmetRecipesApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initTimber()
    }

    /**
     * 初始化日志打印
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}