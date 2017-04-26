package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.FriendRankingItemViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/4/26.
 */

public class FriendRankingViewModel extends ACollectionViewModel<RunBean> {
    private int num = 1;
    private int flag;
    public FriendRankingViewModel(Fragment fragment, String type) {
        super(fragment, false, true);
        this.flag = getFlag(type);
    }

    private int getFlag(String type) {
        switch (type) {
            case Constants.TYPE_DAY:
                return 4;
            case Constants.TYPE_WEEK:
                return 3;
            case Constants.TYPE_MONTH:
                return 2;
            default:
                return -1;
        }
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<RunBean>> getData(RefreshMode mode) {
                return HttpMethods.getInstance()
                        .getFriendRankList(flag, Constants.DEFAULT_PAGE_SIZE, num)
                        .doOnNext(result -> num = result.getNextPage())
                        .doOnNext(result -> setNextLoadEnable(result.isHasNextPage()))
                        .map(ListResultBean::getList);
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_friend_ranking, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(RunBean item) {
        return new FriendRankingItemViewModel(getFragment().getActivity(), item);
    }
}
