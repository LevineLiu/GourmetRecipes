package com.levine.base.utils

import android.os.Build
import android.text.TextUtils

/**
 * Created by wqd19 on 17/12/11.
 */
object RomUtils {
    private const val TAG = "RomUtils"
    private const val PREFIX_HUAWEI = "HUAWEI"
    private const val PREFIX_XIAOMI = "XIAOMI"
    private const val PREFIX_MEIZU = "MEIZU"
    private const val PREFIX_OTHERS = "OTHERS"

    //MIUI标识
    const val BRAND_MIUI = "xiaomi"

    //EMUI标识
    const val BRAND_EMUI1 = "huawei"
    const val BRAND_EMUI2 = "honor"

    //MEIZU标识
    const val BRAND_MEIZU = "meizu"

    /**
     * @param
     * @return ROM_TYPE ROM类型的枚举
     * @description获取ROM类型: MIUI_ROM, EMUI_ROM, OTHER_ROM
     */
    val romType: ROM_TYPE
        get() {
            val rom_type = ROM_TYPE.OTHER
            val brand = Build.BRAND
            if (!TextUtils.isEmpty(brand)) {
                if (brand.toLowerCase().contains(BRAND_EMUI1) || brand.toLowerCase().contains(BRAND_EMUI2)) {
                    return ROM_TYPE.EMUI
                }
                if (brand.toLowerCase().contains(BRAND_MIUI)) {
                    return ROM_TYPE.MIUI
                }
                if (brand.toLowerCase().contains(BRAND_MEIZU)) {
                    return ROM_TYPE.MEIZU
                }
            }
            return rom_type
        }
    //
    //    private static String getSystemProperty(String propName) {
    //        String line;
    //        BufferedReader input = null;
    //        try {
    //            getProcess p = Runtime.getRuntime().exec("getprop " + propName);
    //            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
    //            line = input.readLine();
    //            input.close();
    //        } catch (IOException ex) {
    //            Log.e(TAG, "Unable to read sysprop " + propName, ex);
    //            return null;
    //        } finally {
    //            if (input != null) {
    //                try {
    //                    input.close();
    //                } catch (IOException e) {
    //                    Log.e(TAG, "Exception while closing InputStream", e);
    //                }
    //            }
    //        }
    //        return line;
    //    }
    /**
     * 判断是否为华为UI
     */
    val isHuaweiRom: Boolean
        get() = romType == ROM_TYPE.EMUI

    /**
     * 判断是否为小米UI
     */
    val isMiuiRom: Boolean
        get() = romType == ROM_TYPE.MIUI

    /**
     *
     * "ro.build.user" -> "flyme"
     * "persist.sys.use.flyme.icon" -> "true"
     * "ro.flyme.published" -> "true"
     * "ro.build.display.id" -> "Flyme OS 5.1.2.0U"
     * "ro.meizu.setupwizard.flyme" -> "true"
     *
     * 判断是否为魅族UI
     * @return
     */
    val isFlymeRom: Boolean
        get() = romType == ROM_TYPE.MEIZU

    enum class ROM_TYPE(val prefix: String) {
        MIUI(PREFIX_XIAOMI), EMUI(PREFIX_HUAWEI), MEIZU(PREFIX_MEIZU), OTHER(PREFIX_OTHERS);

    }
}