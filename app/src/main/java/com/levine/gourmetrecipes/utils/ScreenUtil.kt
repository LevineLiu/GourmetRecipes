package com.levine.gourmetrecipes.utils

import com.levine.gourmetrecipes.GourmetRecipesApplication

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/9 11:02
 * 描    述：屏幕相关工具类
 * 修订历史：
 */

/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun dp2px(dp: Float): Int {
    return (dp * getDensity() + 0.5f).toInt()
}

/**
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 */
fun px2dp(px: Int): Float {
    return px/ getDensity() + 0.5f
}

private fun getDensity(): Float {
    return GourmetRecipesApplication.context.resources.displayMetrics.density
}

/**
 * 屏幕宽度
 */
val screenWidth
    get() = GourmetRecipesApplication.context.resources.displayMetrics.widthPixels

/**
 * 屏幕高度
 */
val screenHeight
    get() = GourmetRecipesApplication.context.resources.displayMetrics.heightPixels
