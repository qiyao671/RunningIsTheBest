package com.qiyao.bysj.baselibrary.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.qiyao.bysj.baselibrary.R;

import java.lang.reflect.Method;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 17:43.
 * 类描述：
 */

public class FragmentContainerActivity extends BaseActivity {
    private int overrideTheme = -1;

    public static void launch(Context context, Class<? extends Fragment> clazz, Bundle args, int flags) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        if (flags != -1) {
            intent.addFlags(flags);
        }
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtras(args);
        context.startActivity(intent);
    }
    /**
     * 启动一个界面
     *
     * @param context
     * @param clazz
     * @param args
     */
    public static void launch(Context context, Class<? extends Fragment> clazz, Bundle args) {
        launch(context, clazz, args, -1);
    }

    public static void launch(Context context, Class<? extends Fragment> clazz, int flags) {
        launch(context, clazz, null, flags);
    }

    public static void launch(Context context, Class<? extends Fragment> clazz) {
        launch(context, clazz, null);
    }

    public static void launchForResult(Fragment from, Class<? extends Fragment> clazz, Bundle args, int requestCode) {
        Intent intent = new Intent(from.getActivity(), FragmentContainerActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtras(args);
        from.startActivityForResult(intent, requestCode);
    }

    public static void launchForResult(Fragment from, Class<? extends Fragment> clazz, int requestCode) {
        launchForResult(from, clazz, null, requestCode);
    }

    public static void launchForResult(Activity from, Class<? extends Fragment> clazz, Bundle args, int requestCode) {
        Intent intent = new Intent(from, FragmentContainerActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtras(args);
        from.startActivityForResult(intent, requestCode);
    }

    public static void launchForResult(Activity from, Class<? extends Fragment> clazz, int requestCode) {
        launchForResult(from, clazz, null, requestCode);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String className = getIntent().getStringExtra("className");
        if (TextUtils.isEmpty(className)) {
            finish();
            return;
        }

        int contentId = R.layout.activity_fragment_container;

        Bundle values = getIntent().getExtras();

        Fragment fragment = null;
        if (savedInstanceState == null) {
            try {
                Class clazz = Class.forName(className);
                fragment = (Fragment) clazz.newInstance();
                // 设置参数给Fragment
                if (values != null) {
                    try {
                        Method method = clazz.getMethod("setArguments", Bundle.class);
                        method.invoke(fragment, values);
                    } catch (Exception e) {
                    }
                }
                // 重写Activity的主题
                try {
                    Method method = clazz.getMethod("setTheme");
                    if (method != null)
                        overrideTheme = Integer.parseInt(method.invoke(fragment).toString());
                } catch (Exception e) {
                }
                // 重写Activity的contentView
                try {
                    Method method = clazz.getMethod("setActivityContentView");
                    if (method != null)
                        contentId = Integer.parseInt(method.invoke(fragment).toString());
                } catch (Exception e) {
                }
            } catch (Exception e) {
                e.printStackTrace();
                finish();
                return;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(contentId);

//        BizFragment.getBizFragment(this);

        if (fragment != null) {
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment, makeFragmentName(className)).commit();
        }
        setDisplayHomeAsUpEnabled(false);
    }

    public static String makeFragmentName(String className) {
        return "TAG_" + className;
    }


}
