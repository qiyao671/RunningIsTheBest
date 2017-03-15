package com.qiyao.bysj.baselibrary.ui.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by qiyao on 2017/3/10.
 */

public class ABaseFragment extends RxFragment {
    protected void setTitle(String title) {
        if (getActivity() != null) {
            getActivity().setTitle(title);
        }
    }
    protected void setTitle(int title) {
        if (getActivity() != null) {
            getActivity().setTitle(title);
        }
    }

    protected void setDisplayHomeAsUpEnabled(boolean enabled) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }
}
