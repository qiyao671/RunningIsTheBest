package com.qiyao.bysj.runningisthebest.common;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;

import java.util.Locale;

/**
 * Created by qiyao on 2017/3/14.
 */

public class AppTimeUtils {
    public static String getPace(long duration, double distance) {
        long mills = (long) (duration / distance);
        long second = mills % ConstUtils.MIN / ConstUtils.SEC;
        long min = mills % ConstUtils.HOUR / ConstUtils.MIN;
        return String.format(Locale.CHINA, "%02d'%02d\"", min, second);
    }

    public static String getTime(long mills) {
        long second = mills % ConstUtils.MIN / ConstUtils.SEC;
        long min = mills % ConstUtils.HOUR / ConstUtils.MIN;
        long hour = mills / ConstUtils.HOUR;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
    }
}
