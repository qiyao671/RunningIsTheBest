package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.BR;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item.FriendRequestItemViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/15 21:33.
 * 类描述：
 */

public class FriendRequestsViewModel extends ACollectionViewModel<UserBean> {
    public FriendRequestsViewModel(Fragment fragment) {
        super(fragment, false, false);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<UserBean>> getData(RefreshMode mode) {
                return HttpMethods.getInstance()
                        .listFriendRequests();
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_friend_request, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(UserBean item) {
        return new FriendRequestItemViewModel(getFragment().getActivity(), item);
    }
}
