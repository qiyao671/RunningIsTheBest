package com.qiyao.bysj.runningisthebest.module;

import android.content.Intent;
import android.os.Bundle;

import com.qiyao.bysj.runningisthebest.base.AppBaseActivity;
import com.qiyao.bysj.runningisthebest.module.login.ui.LoginFragment;
import com.qiyao.bysj.runningisthebest.module.MainActivity;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 15:16.
 * 类描述：
 */

public class SplashActivity extends AppBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        launchNextActivity();
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
        LoginFragment.launch(this);
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
