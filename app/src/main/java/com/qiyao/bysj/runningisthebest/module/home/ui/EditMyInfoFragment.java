package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.EditMyInfoViewModel;

/**
 * Created by qiyao on 2017/3/15.
 */

public class EditMyInfoFragment extends ADataBindingFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.edit_info);
        setHasOptionsMenu(true);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        UserBean userBean = arguments.getParcelable(EditMyInfoViewModel.KEY_USER_BEAN);
        return new EditMyInfoViewModel(this, userBean);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_edit_my_info;
    }

    public static void launch(Activity activity, UserBean userBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EditMyInfoViewModel.KEY_USER_BEAN, userBean);
        FragmentContainerActivity.launch(activity, EditMyInfoFragment.class, bundle);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tb_complete, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.complete) {
            ((EditMyInfoViewModel)getViewModel()).submitUpdate();
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
