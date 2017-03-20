package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentContentViewModel implements IViewModel {
    private Context context;

    private String userName;
    private String profileUrl;
    private String datetime;
    private String content;
    private List<String> imageUrls;
    public ObservableField<View.OnClickListener> onClickListener = new ObservableField<>();

    private MomentBean momentBean;

    public MomentContentViewModel(Context context, MomentBean momentBean) {
        this.context = context;
        this.momentBean = momentBean;
        setMoment(momentBean);
    }

    private void setMoment(MomentBean momentBean) {
        // TODO: 2017/3/17
        datetime = TimeUtils.getFriendlyTimeSpanByNow(momentBean.getGmtCreate());
        content = momentBean.getContent();
        imageUrls = Arrays.asList(momentBean.getPicture().split(","));
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    @Bindable
    public String getProfileUrl() {
        return profileUrl;
    }

    @Bindable
    public String getDatetime() {
        return datetime;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    @Bindable
    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setOnClickListener(ObservableField<View.OnClickListener> onClickListener) {
        this.onClickListener = onClickListener;
    }
}
