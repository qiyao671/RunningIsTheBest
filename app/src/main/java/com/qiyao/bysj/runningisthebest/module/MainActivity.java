package com.qiyao.bysj.runningisthebest.module;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.ui.HomeFragment;
import com.qiyao.bysj.runningisthebest.base.AppBaseActivity;
import com.qiyao.bysj.runningisthebest.module.moment.ui.MomentFragment;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunFragment;

public class MainActivity extends AppBaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigation;
    private int currentTabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigation();
        //默认显示跑步界面
        selectTab(R.id.tab_run);
    }

    /**
     * 初始化底边栏
     */
    private void initBottomNavigation() {
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 选择tab
     * @param tabId tab的tabId
     */
    private void selectTab(int tabId) {
        MenuItem item = bottomNavigation.getMenu().findItem(tabId);
        item.setChecked(true);
        this.onNavigationItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //如果是同一个，则不做任何操作
        if (item.getItemId() == currentTabId) {
            return false;
        }

        String fragmentName = getFragmentName(item.getItemId());
        if (StringUtils.isEmpty(fragmentName)) {
            return false;
        } else {
            selectFragment(fragmentName);
            currentTabId = item.getItemId();    //设置当前的tab
            return true;
        }
    }

    /**
     * 选择显示fragment
     * @param fragmentName 要显示的fragment的name
     */
    private void selectFragment(String fragmentName) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideCurrentFragmentIfExist(transaction);    //隐藏当前fragment
        addOrShowFragment(transaction, fragmentName);   //显示被选择的fragment
        transaction.commit();
    }

    /**
     * 如果存在当前显示的fragment,则隐藏这个fragment
     * @param transaction FragmentTransaction
     */
    private void hideCurrentFragmentIfExist(FragmentTransaction transaction) {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
    }

    /**
     * 如果这个fragment已经add过了,则show，否则add
     * @param transaction fragment transaction
     * @param fragmentName 要显示的fragment的name
     */
    private void addOrShowFragment(FragmentTransaction transaction, String fragmentName) {
        Fragment fragment = newOrFindFragment(fragmentName);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_container, fragment, getFragmentTag(fragmentName));
        } else { //如果add过了，则show这个fragment
            transaction.show(fragment);
        }
    }

    /**
     *
     * @return 当前显示的fragment
     */
    private Fragment getCurrentFragment() {
        return findFragment(getFragmentName(currentTabId));
    }

    /**
     * 获得tab对应的fragment的name
     * @param tabId tab的id
     * @return fragmentName
     */
    private String getFragmentName(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                return HomeFragment.class.getName();
            case R.id.tab_run:
                return RunFragment.class.getName();
            case R.id.tab_moment:
                return MomentFragment.class.getName();
            default:
                return null;
        }
    }

    /**
     * 如果这个fragment存在，则返回这个fragment，如果不存在，new一个再返回
     * @param fragmentName fragment的name
     * @return fragment
     */
    private Fragment newOrFindFragment(String fragmentName) {
        Fragment fragment = Fragment.instantiate(this, fragmentName);
        return fragment != null ? fragment : findFragment(fragmentName);
    }

    /**
     * 通过fragmentName来找到fragment
     * @param fragmentName fragment的name
     * @return fragment
     */
    private Fragment findFragment(String fragmentName) {
        return getFragmentManager().findFragmentByTag(getFragmentTag(fragmentName));
    }

    /**
     * 获得fragment的tag
     * @param fragmentName fragment的name
     * @return fragment的tag
     */
    public String getFragmentTag(String fragmentName) {
        return "MainActivity_" + fragmentName;
    }
}
