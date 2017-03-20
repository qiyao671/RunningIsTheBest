package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentMomentsBinding;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentsViewModel;

/**
 * Created by qiyao on 2017/3/8.
 */

public class MomentsFragment extends ARecyclerViewFragment {
    private static final String KEY_USER = "USER";
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setHasOptionsMenu(true);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = FragmentMomentsBinding.inflate(getActivity().getLayoutInflater()).toolbar;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.tab_moment);
            activity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        if (arguments == null) {
            return new MomentsViewModel(this);
        } else {
            UserBean user = arguments.getParcelable(KEY_USER);
            return new MomentsViewModel(this, user);
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_moments;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tb_add_moment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_moment) {
            FragmentContainerActivity.launch(getActivity(), WriteMomentFragment.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launch(Context context, UserBean userBean) {
        Bundle bundle = null;
        if (userBean != null) {
            bundle = new Bundle();
            bundle.putParcelable(KEY_USER, userBean);
        }
        FragmentContainerActivity.launch(context, MomentsFragment.class, bundle);
    }

    public static void launch(Context context) {
        launch(context, null);
    }
}
