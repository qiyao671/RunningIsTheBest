package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.ApproveBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.MomentLikeItemViewModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentLikesViewModel extends BaseObservable implements IViewModel, MomentContentViewModel.OnLikeMomentSuccessListener {
    private Context context;

    private List<ApproveBean> approveBeanList;

    private ItemBinding<ApproveBean> likeItemView;
    public ObservableArrayList<IItemViewModel> likes = new ObservableArrayList<>();

    public MomentLikesViewModel(Context context, List<ApproveBean> approveBeanList) {
        this.context = context;
        initItemView();
        initItems(approveBeanList);
    }

    public void initItems(List<ApproveBean> approveBeanList) {
        this.approveBeanList = approveBeanList;
        Observable.from(approveBeanList)
                .map(approve -> new MomentLikeItemViewModel(context, approve))
                .subscribe(likes::add);
    }

    private void initItemView() {
        likeItemView = ItemBinding.of(BR.viewModel, R.layout.item_moment_like);
    }

    @Bindable
    public ItemBinding<ApproveBean> getLikeItemView() {
        return likeItemView;
    }

    @Override
    public void onLikeMomentSuccess(boolean isLike) {
        UserBean userBean = SPHelper.loadUser();
        if (isLike) {
            userBean.setRelationStatus(Constants.FRIEND_STATUS_IS_MYSELF);
            ApproveBean approveBean = new ApproveBean();
            approveBean.setUserId(userBean.getId());
            approveBean.setUser(userBean);
            likes.add(new MomentLikeItemViewModel(context, approveBean));
        } else {
            for (IItemViewModel like : likes) {
                MomentLikeItemViewModel momentLikeItemViewModel = (MomentLikeItemViewModel) like;
                if (momentLikeItemViewModel.getUserId() == userBean.getId()) {
                    likes.remove(like);
                    break;
                }
            }
        }
    }
}
