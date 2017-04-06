package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.module.home.ui.OfflineMapPagerFragment;
import com.qiyao.bysj.runningisthebest.module.login.ui.LoginFragment;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 14:36.
 * 类描述：
 */

public class SettingViewModel implements IViewModel {
    private Fragment fragment;

    public SettingViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_out:
                signOut();
                break;
            case R.id.tv_offline_map:
                FragmentContainerActivity.launch(fragment.getActivity(), OfflineMapPagerFragment.class);
                break;
        }
    }

    private void signOut() {
        SPHelper.removeToken();
        SPHelper.removeUser();
        AppApplication.instance().loadToken();
        LoginFragment.launchAndClearTask(fragment.getActivity());
    }
}
