package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.MomentItemViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/4/25.
 */

public class UserMomentsViewModel extends ACollectionViewModel<MomentBean> {
    private Integer userId;
    private Integer num;
    public UserMomentsViewModel(Fragment fragment) {
        super(fragment, false, true);
    }

    public UserMomentsViewModel(Fragment fragment, int userId) {
        super(fragment, false, true);
        this.userId = userId;
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<MomentBean>> getData(RefreshMode mode) {
                return HttpMethods.getInstance()
                        .getMomentListOfUser(userId, num, Constants.DEFAULT_PAGE_SIZE)
                        .doOnNext(result -> setNextLoadEnable(result.isHasNextPage()))
                        .doOnNext(result -> num = result.getNextPage())
                        .map(ListResultBean::getList);
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_moment, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(MomentBean item) {
        return new MomentItemViewModel(getFragment().getActivity(), item);
    }
}
