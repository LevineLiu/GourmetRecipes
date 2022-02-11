package com.levine.gourmetrecipes.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.levine.gourmetrecipes.utils.dp2px
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/9 14:10
 * 描    述：ImageView扩展函数
 * 修订历史：
 */

/**
 * 加载圆角图片
 * @param url 图片地址
 * @param radius 圆角
 * @param cornerType 圆角类型
 */
fun ImageView.loadRoundedImage(url: String, radius: Float = 0f, cornerType: RoundedCornersTransformation.CornerType) {
    if (radius == 0f) {
        Glide.with(this.context).load(url).into(this)
    } else {
        val requestOption = RequestOptions.bitmapTransform(RoundedCornersTransformation(dp2px(radius), 0, cornerType))
        Glide.with(this.context).load(url).apply(requestOption).into(this)
    }
}