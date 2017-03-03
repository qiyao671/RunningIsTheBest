package com.qiyao.bysj.runningisthebest.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.ActivityMainBinding;
import com.qiyao.bysj.runningisthebest.ui.activity.base.AppBaseActivity;
import com.qiyao.bysj.runningisthebest.viewmodel.MainViewModel;

public class MainActivity extends AppBaseActivity {
    private IViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel();
        binding.setVariable(BR.viewModel, viewModel);

        initToolbar();
        showRunFragment();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    private void switchFragment(int tabId) {
        Menu menu = binding.bottomNavigation.getMenu();
        menu.findItem(tabId).setChecked(true);
    }

    private void showRunFragment() {
        switchFragment(R.id.tab_run);
    }
}
