package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.friends.ui.UserInfoFragment;
import com.qiyao.bysj.runningisthebest.module.moment.ui.ImagePagerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentContentViewModel extends BaseObservable implements IViewModel, View.OnClickListener, ItemImageClickListener {
    private Context context;

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> profileUrl = new ObservableField<>();
    public ObservableField<String> datetime = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<ArrayList<String>> imageUrls = new ObservableField<>();
    public ObservableBoolean isLike = new ObservableBoolean();

    private View.OnClickListener onCommentClickListener;
    private OnLikeMomentSuccessListener onLikeMomentSuccessListener;

    private MomentBean momentBean;

    public MomentContentViewModel(Context context, MomentBean momentBean) {
        this.context = context;
        setMoment(momentBean);
    }

    public void setMoment(MomentBean momentBean) {
        this.momentBean = momentBean;
        profileUrl.set(momentBean.getUser().getProfile());
        userName.set(momentBean.getUser().getUsername());
        datetime.set(TimeUtils.getFriendlyTimeSpanByNow(momentBean.getGmtCreate()));
        if (momentBean.getContent() != null) {
            content.set(momentBean.getContent());
        }
        if (momentBean.getPicture() != null) {
            ArrayList<String> images = new ArrayList<>();
            images.addAll(Arrays.asList(momentBean.getPicture().split(",")));
            imageUrls.set(images);
        }
        isLike.set(momentBean.getApproved());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                likeMoment();
                break;
            case R.id.civ_user_profile:
                UserInfoFragment.launch(context, momentBean.getUser());
                break;
            case R.id.iv_comment:
                if (onCommentClickListener != null) {
                    onCommentClickListener.onClick(view);
                }
        }
    }

    private void likeMoment() {
        boolean up = !isLike.get();
        HttpMethods.getInstance()
                .likeMoment(momentBean.getId(), up)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> isLike.set(up))
                .doOnNext(s -> momentBean.setApproved(up))
                .doOnNext(s -> {if(onLikeMomentSuccessListener != null) onLikeMomentSuccessListener.onLikeMomentSuccess(up);})
                .subscribe(ToastUtils::showShortToast, e -> ToastUtils.showShortToast(e.getMessage()));
    }

    void setOnCommentClickListener(View.OnClickListener onCommentClickListener) {
        this.onCommentClickListener = onCommentClickListener;
    }

    @Override
    public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
        ImagePagerFragment.launch(context, (ArrayList<String>) imageUrls.get(), index);
    }

    public void setOnLikeMomentSuccessListener(OnLikeMomentSuccessListener onLikeMomentSuccessListener) {
        this.onLikeMomentSuccessListener = onLikeMomentSuccessListener;
    }

    interface OnLikeMomentSuccessListener {
        void onLikeMomentSuccess(boolean isLike);
    }

}
