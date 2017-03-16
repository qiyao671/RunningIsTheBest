package com.qiyao.bysj.runningisthebest.common;

import android.content.Context;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by qiyao on 2017/3/14.
 */

public class MyAppUtils {
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

    public static String readTextFileToStringFromAssets(Context context, String path, String encode) {
        StringBuilder stringBuilder = new StringBuilder();;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(path), encode);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
