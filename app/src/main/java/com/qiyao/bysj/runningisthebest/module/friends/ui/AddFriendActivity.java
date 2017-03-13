package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.qiyao.bysj.baselibrary.common.utils.ScreenUtils;
import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.base.AppBaseActivity;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.UserSearchResultViewModel;

/**
 * Created by qiyao on 2017/3/13.
 */

public class AddFriendActivity extends AppBaseActivity {
    public static final String KEY_QUERY = "KEY_QUERY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        enableDisplayHomeAsUp();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String query = intent.getStringExtra(KEY_QUERY);
        if (StringUtils.isEmpty(query)) {
            return;
        }
        String fragmentTag = FragmentContainerActivity.makeFragmentName(UserSearchResultFragment.class.getName());
        Fragment fragment = getFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, UserSearchResultFragment.newInstance(query), fragmentTag);
            ft.commit();
        } else {
            ((UserSearchResultViewModel)((UserSearchResultFragment)fragment).getViewModel()).executeQuery(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tb_add_friend, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    public static void launch(Activity activity, String query) {
        Intent intent = new Intent(activity, AddFriendActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(AddFriendActivity.KEY_QUERY, query);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AddFriendActivity.class);
        activity.startActivity(intent);
    }
}
