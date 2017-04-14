package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.ApproveBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.MomentLikeItemViewModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentLikesViewModel extends BaseObservable implements IViewModel {
    private Context context;

    private List<ApproveBean> approveBeanList;

    private ItemBinding<ApproveBean> likeItemView;
    public ObservableArrayList<IItemViewModel> likes = new ObservableArrayList<>();

    public MomentLikesViewModel(Context context, List<ApproveBean> approveBeanList) {
        this.context = context;
        this.approveBeanList = approveBeanList;
        initItemView();
        initItems();
    }

    private void initItems() {
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
}
