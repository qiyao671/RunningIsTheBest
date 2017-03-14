package com.qiyao.bysj.runningisthebest.common;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;

/**
 * Created by qiyao on 2017/3/14.
 */

public class AppTimeUtils {
    public static String getPace(long duration, double distance) {
        long mills = (long) (duration / distance);
        long second = mills % ConstUtils.MIN;
        long min = mills % ConstUtils.HOUR;
        return min + "'" + second + "\"";
    }

    public static String getTime(long mills) {
        long second = mills % ConstUtils.MIN;
        long min = mills % ConstUtils.HOUR;
        long hour = mills / ConstUtils.HOUR;
        return hour + ":" + min + ":" + second;
    }
}
