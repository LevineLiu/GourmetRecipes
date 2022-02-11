package com.levine.base.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.graphics.ColorUtils
import java.lang.Exception

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2021/4/25 16:03
 * 描    述：状态栏工具类
 * 修订历史：
 */
object SystemBarUtil {
    /**
     * 设置透明状态栏
     * @param activity activity
     */
    fun setTransparentStatusBar(activity: Activity?) {
        setStatusBarColor(activity, Color.TRANSPARENT)
    }

    /**
     * 设置状态栏颜色
     * @param activity activity
     * @param statusBarColor 状态栏颜色
     */
    fun setStatusBarColor(activity: Activity?, statusBarColor: Int) {
        setStatusBarColor(activity, statusBarColor, true)
    }

    /**
     * 设置状态栏颜色
     * @param activity activity
     * @param statusBarColor 状态栏颜色
     * @param isReserveStatusBarSpace 是否保留状态栏的空间
     */
    fun setStatusBarColor(activity: Activity?, statusBarColor: Int, isReserveStatusBarSpace: Boolean) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //系统版本5.0以上
                setStatusBarColor(activity.window, statusBarColor, isReserveStatusBarSpace)
            }
        }
    }

    /**
     * 系统5.0以上设置状态栏颜色
     * @param window window
     * @param color 状态栏颜色
     * @param isReserveStatusBarSpace 是否保留状态栏的空间
     */
    private fun setStatusBarColor(window: Window, color: Int, isReserveStatusBarSpace: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!isReserveStatusBarSpace) {
                //不保留状态栏空间，允许内容侵入状态栏
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            //要清除FLAG_TRANSLUCENT_STATUS，FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS才能生效
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //6.0以上判断状态栏是深色还是浅色，从而设置状态栏的文字图标为浅色还是深色，以防看不到文字和图标
                setSystemBarLightMode(window, !isDarkColor(color))
            }
            window.statusBarColor = color
        }
    }

    /**
     * 获取状态栏高度
     * @param context 上下文
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelOffset(resourceId)
    }

    /**
     * 设置状态栏文字是深色还是浅色
     * @param window window
     * @param isDark 是否是深色
     */
    fun setSystemBarLightMode(window: Window, isDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.0以上统一用系统api
            val systemUiVisibility = window.decorView.systemUiVisibility
            if (isDark) {
                //深色
                window.decorView.systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                //浅色
                window.decorView.systemUiVisibility = systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0到6.0，小米和魅族支持改变文字颜色
            if (RomUtils.isMiuiRom) {
                setMIUISystemBarLightMode(window, isDark)
            } else if (RomUtils.isFlymeRom) {
                setFlymeSystemBarLightMode(window, isDark)
            }
        }
    }

    /**
     * 小米设置状态栏文字颜色
     * @param window window
     * @param isDark 是否是深色
     */
    private fun setMIUISystemBarLightMode(window: Window, isDark: Boolean) {
        try {
            val clazz: Class<out Window> = window.javaClass
            val darkModeFlag: Int
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField =
                clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(window, if (isDark) darkModeFlag else 0, darkModeFlag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 魅族设置状态栏文字颜色
     * @param window window
     * @param isDark 是否是深色
     */
    private fun setFlymeSystemBarLightMode(window: Window, isDark: Boolean) {
        try {
            val lp = window.attributes
            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (isDark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            window.attributes = lp
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 是否是深色
     * @param color 颜色
     * @return 是否是深色
     */
    fun isDarkColor(color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) < 0.5
    }

    /**
     * 隐藏状态栏
     * @param activity activity
     */
    fun hideStatusBar(activity: Activity?) {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * 全屏，同时隐藏状态栏和导航栏
     * @param activity activity
     */
    fun showFullScreen(activity: Activity?) {
        if (activity != null) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    fun hideNavigation(activity: Activity?) {
        if (activity != null) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    fun getNavigationBarHeight(activity: Activity): Int {
        val resources = activity.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }
}