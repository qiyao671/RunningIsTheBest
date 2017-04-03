package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.UserInfoViewModel;

/**
 * Created by qiyao on 2017/3/16.
 */

public class UserInfoFragment extends ADataBindingFragment {
    private static final String KEY_USER = "USER";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.detail_info);
        setDisplayHomeAsUpEnabled(true);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new UserInfoViewModel(this, (UserBean) arguments.get(KEY_USER));
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_user_info;
    }

    public static void launch(Context context, UserBean userBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, userBean);
        FragmentContainerActivity.launch(context, UserInfoFragment.class, bundle);
    }
}
