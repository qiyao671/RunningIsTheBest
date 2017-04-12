package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.MomentItemViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/3/8.
 */

public class MomentsViewModel extends ACollectionViewModel<MomentBean> {
    private UserBean user;

    public MomentsViewModel(Fragment fragment) {
        super(fragment);
    }

    public MomentsViewModel(Fragment fragment, UserBean userId) {
        super(fragment);
        this.user = userId;
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new GetMomentsTask(refreshMode).execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_moment, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(MomentBean item) {
        return new MomentItemViewModel(getFragment().getActivity(), item);
    }

    private class GetMomentsTask extends APagingTask {
        private Integer sinceId = null;
        private Integer endId = null;
        private Integer pageSize = 15;

        GetMomentsTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected Observable<List<MomentBean>> getData(RefreshMode mode) {
            HttpMethods httpMethods = HttpMethods.getInstance();
            if (user != null) {
                // TODO: 2017/3/17
                return null;
            } else {
                return httpMethods.getRecentMoments(sinceId, endId, pageSize)
                        .doOnNext(list -> setPageInfo(list, mode))
                        .doOnNext(momentBeen -> {
                            if (mode == RefreshMode.reset || mode == RefreshMode.load_more) {
                                setNextLoadEnable(momentBeen.size() < pageSize);
                            }
                        });
            }
        }

        private void setPageInfo(List<MomentBean> list, RefreshMode mode) {
            switch (mode) {
                case load_more:
                    endId = list.get(list.size() - 1).getId();
                    break;
                case refresh:
                    sinceId = list.get(0).getId();
                    break;
                case reset:
                    sinceId = list.get(0).getId();
                    endId = list.get(list.size() - 1).getId();
                    break;
            }
        }
    }
}
