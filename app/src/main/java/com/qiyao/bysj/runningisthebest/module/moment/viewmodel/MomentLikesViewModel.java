package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentLikesViewModel extends BaseObservable implements IViewModel {
    private Context context;

    private List<UserBean> likeUserBeanList;

    private ItemBinding<UserBean> likeItemView;
    public ObservableArrayList<IItemViewModel> likes = new ObservableArrayList<>();

    public MomentLikesViewModel(Context context, List<UserBean> likeUserBeanList) {
        this.context = context;
        this.likeUserBeanList = likeUserBeanList;
        initItemView();
        initItems();
    }

    private void initItems() {
        Observable.from(likeUserBeanList)
                .map(userBean -> new MomentLikeItemViewModel(context, userBean))
                .subscribe(likes::add);
    }

    private void initItemView() {
        likeItemView = ItemBinding.of(BR.viewModel, R.layout.item_moment_like);
    }

    @Bindable
    public ItemBinding<UserBean> getLikeItemView() {
        return likeItemView;
    }
}
