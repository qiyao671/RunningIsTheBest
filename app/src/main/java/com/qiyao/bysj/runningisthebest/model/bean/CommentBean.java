package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/15 22:43.
 * 类描述：
 */

public class CommentBean implements Parcelable {
    private Integer id;

    private Integer momentId;

    private String content;

    private Integer userId;

    private Integer repliedUserId;

    private Short status;

    private Long gmtCreate;

    private Long gmtModified;

    private UserBean user;

    private UserBean repliedUser;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRepliedUserId() {
        return repliedUserId;
    }

    public void setRepliedUserId(Integer repliedUserId) {
        this.repliedUserId = repliedUserId;
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

    public UserBean getRepliedUser() {
        return repliedUser;
    }

    public void setRepliedUser(UserBean repliedUser) {
        this.repliedUser = repliedUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.momentId);
        dest.writeString(this.content);
        dest.writeValue(this.userId);
        dest.writeValue(this.repliedUserId);
        dest.writeValue(this.status);
        dest.writeValue(this.gmtCreate);
        dest.writeValue(this.gmtModified);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.repliedUser, flags);
    }

    public CommentBean() {
    }

    protected CommentBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.momentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.content = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.repliedUserId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = (Short) in.readValue(Short.class.getClassLoader());
        this.gmtCreate = (Long) in.readValue(Long.class.getClassLoader());
        this.gmtModified = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.repliedUser = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommentBean> CREATOR = new Parcelable.Creator<CommentBean>() {
        @Override
        public CommentBean createFromParcel(Parcel source) {
            return new CommentBean(source);
        }

        @Override
        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };
}