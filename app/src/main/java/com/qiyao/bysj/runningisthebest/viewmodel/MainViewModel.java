package com.qiyao.bysj.runningisthebest.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;

/**
 * Created by qiyao on 2017/3/3.
 */

public class MainViewModel extends BaseObservable implements IViewModel, BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener;

    public MainViewModel() {
        onNavigationItemSelectedListener = this;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                return false;
        }
    }

    @Bindable
    public BottomNavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return onNavigationItemSelectedListener;
    }
}
