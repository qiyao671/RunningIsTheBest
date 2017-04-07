package com.qiyao.bysj.runningisthebest.module;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.base.AppBaseActivity;
import com.qiyao.bysj.runningisthebest.module.login.ui.LoginFragment;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 15:16.
 * 类描述：
 */

public class SplashActivity extends AppBaseActivity {
    private static final int SPLASH_TIME = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acticity_splash);
        Handler handler = new Handler();
        handler.postDelayed(this::launchNextActivity, SPLASH_TIME);
    }

    private void launchNextActivity() {
        if (hasLogged()) {
            launchMainActivity();
        } else {
//            launchLoginActivity();
            launchMainActivity();
        }
        finish();
    }

    private void launchLoginActivity() {
        LoginFragment.launchAndClearTask(this);
    }

    private void launchMainActivity() {
        MainActivity.launch(this);
    }
}
