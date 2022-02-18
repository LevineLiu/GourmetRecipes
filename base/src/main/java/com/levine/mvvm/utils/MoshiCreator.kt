package com.levine.mvvm.utils

import com.squareup.moshi.Moshi

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/10 15:51
 * 描    述：
 * 修订历史：
 */
object MoshiCreator {
    val moshi: Moshi = Moshi.Builder().build()
}