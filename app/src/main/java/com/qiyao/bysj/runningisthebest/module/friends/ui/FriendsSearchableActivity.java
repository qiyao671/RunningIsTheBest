package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.qiyao.bysj.runningisthebest.base.AppBaseActivity;

/**
 * Created by qiyao on 2017/3/13.
 */

public class FriendsSearchableActivity extends AppBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            AddFriendActivity.launch(this, query);
        }
        finish();
    }
}
