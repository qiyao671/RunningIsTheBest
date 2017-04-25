package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.UserMomentsViewModel;

/**
 * Created by qiyao on 2017/4/25.
 */

public class UserMomentsFragment extends ARecyclerViewFragment {
    private static final String KEY_USER_ID = "USER_ID";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideToolbar();
        setTitle(R.string.user_moment);
        setDisplayHomeAsUpEnabled(true);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        if (arguments != null) {
            return new UserMomentsViewModel(this, arguments.getInt(KEY_USER_ID));
        } else {
            return new UserMomentsViewModel(this);
        }
    }

    public static void launch(Context context, int userId) {
        Bundle args = new Bundle();
        args.putInt(KEY_USER_ID, userId);
        FragmentContainerActivity.launch(context, UserMomentsFragment.class, args);
    }
}
