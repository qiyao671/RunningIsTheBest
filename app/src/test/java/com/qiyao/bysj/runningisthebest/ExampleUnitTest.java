package com.qiyao.bysj.runningisthebest;

import android.provider.Settings;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void format() {
        System.out.println("format: " + String.format(Locale.US, "%tHH", (long)ConstUtils.DAY));
    }
}