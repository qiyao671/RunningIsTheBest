package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiyao on 2017/4/14.
 */

public class ApproveBean implements Parcelable {
    private Integer id;

    private Integer momentId;

    private Integer userId;

    private Short status;

    private Long gmtCreate;

    private Long gmtModified;

    /*非数据库*/
    private UserBean user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.momentId);
        dest.writeValue(this.userId);
        dest.writeValue(this.status);
        dest.writeValue(this.gmtCreate);
        dest.writeValue(this.gmtModified);
        dest.writeParcelable(this.user, flags);
    }

    public ApproveBean() {
    }

    protected ApproveBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.momentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = (Short) in.readValue(Short.class.getClassLoader());
        this.gmtCreate = (Long) in.readValue(Long.class.getClassLoader());
        this.gmtModified = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApproveBean> CREATOR = new Parcelable.Creator<ApproveBean>() {
        @Override
        public ApproveBean createFromParcel(Parcel source) {
            return new ApproveBean(source);
        }

        @Override
        public ApproveBean[] newArray(int size) {
            return new ApproveBean[size];
        }
    };
}
