package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.friends.ui.UserInfoFragment;
import com.qiyao.bysj.runningisthebest.module.moment.ui.MomentDetailFragment;

import java.util.Arrays;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentContentViewModel extends BaseObservable implements IViewModel, View.OnClickListener {
    private Context context;

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> profileUrl = new ObservableField<>();
    public ObservableField<String> datetime = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<List<String>> imageUrls = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();

    private MomentBean momentBean;

    public MomentContentViewModel(Context context, MomentBean momentBean) {
        this.context = context;
        this.momentBean = momentBean;
        setMoment(momentBean);
    }

    private void setMoment(MomentBean momentBean) {
        // TODO: 2017/3/17
        profileUrl.set(momentBean.getUser().getProfile());
        userName.set(momentBean.getUser().getUsername());
        datetime.set(TimeUtils.getFriendlyTimeSpanByNow(momentBean.getGmtCreate()));
        if (momentBean.getContent() != null) {
            content.set(momentBean.getContent());
        }
        if (momentBean.getPicture() != null) {
            imageUrls.set(Arrays.asList(momentBean.getPicture().split(",")));
        }
        isLike.set(momentBean.getApproved());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                HttpMethods.getInstance()
                        .likeMoment(momentBean.getId(), !isLike.get())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            ToastUtils.showShortToast(s);
                            isLike.set(!isLike.get());
                        }, e -> ToastUtils.showShortToast(e.getMessage()));
                break;
            case R.id.civ_user_profile:
                UserInfoFragment.launch(context, momentBean.getUser());
                break;
        }
    }
}
