package com.qiyao.bysj.baselibrary.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.qiyao.bysj.baselibrary.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyao on 2017/1/24.
 */

public class BaseActivity extends RxAppCompatActivity {
    private static BaseActivity currentActivity;

    private Toolbar mToolbar;

    private static Map<String, Long> markTimeMap = new HashMap<>();

    public static BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(BaseActivity activity) {
        currentActivity = activity;
    }
    /**
     * 设置时间记号,用来计算2次时间间隔
     */

    protected void setMarkTime(String tag) {
        markTimeMap.put(tag, System.currentTimeMillis());
    }

    /**
     * 时间间隔是否<shortTime
     */
    private boolean isShortTime(String tag, long shortTime) {
        long now = System.currentTimeMillis();
        Long last = markTimeMap.get(tag);
        if (last == null) {
            last = 0L;
        }
        return now - last < shortTime;
    }

    /**
     * 时间间隔是否<shortTime
     * 如果<shortTime,就设置一个新的标记(因为这个时候调用者又会触发一次)
     * 不能短时间多次点击的地方需要
     */
    public boolean isShortTimeFromLast(String tag) {
        long SHORT_TIME = 500;
        boolean isShort = isShortTime(tag, SHORT_TIME);
        if (!isShort) {
            setMarkTime(tag);
        }
        return isShort;
    }
    public boolean isShortTimeFromLast(String tag,long shortTime) {
        boolean isShort = isShortTime(tag, shortTime);
        if (!isShort) {
            setMarkTime(tag);
        }
        return isShort;
    }
    public void setDisplayHomeAsUpEnabled(boolean enabled) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
    }

    public void enableDisplayHomeAsUp() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
       // 如果设备有实体MENU按键，overflow菜单不会再显示
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
        if (viewConfiguration.hasPermanentMenuKey()) {
            try {
                Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(viewConfiguration, false);
            } catch (Exception e) {
            }
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setToolbar();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setToolbar();

    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected Bundle getData() {
        return getIntent().getExtras();
    }
}
