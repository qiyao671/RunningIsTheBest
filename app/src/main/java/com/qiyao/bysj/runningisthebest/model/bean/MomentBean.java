package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/14 21:59.
 * 类描述：
 */

public class MomentBean implements Parcelable {
    private Integer id;

    private Integer userId;

    private String title;

    private String picture;

    private Long gmtCreate;

    private Long gmtModified;

    private Short status;

    private String content;

    private Boolean isApproved;

    private List<CommentBean> commentList;

//    private List<UserBean> approveList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public List<CommentBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentBean> commentList) {
        this.commentList = commentList;
    }

/*    public List<UserBean> getApproveList() {
        return approveList;
    }

    public void setApproveList(List<UserBean> approveList) {
        this.approveList = approveList;
    }*/


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.userId);
        dest.writeString(this.title);
        dest.writeString(this.picture);
        dest.writeValue(this.gmtCreate);
        dest.writeValue(this.gmtModified);
        dest.writeValue(this.status);
        dest.writeString(this.content);
        dest.writeValue(this.isApproved);
        dest.writeList(this.commentList);
//        dest.writeTypedList(this.approveList);
    }

    public MomentBean() {
    }

    protected MomentBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.picture = in.readString();
        this.gmtCreate = (Long) in.readValue(Long.class.getClassLoader());
        this.gmtModified = (Long) in.readValue(Long.class.getClassLoader());
        this.status = (Short) in.readValue(Short.class.getClassLoader());
        this.content = in.readString();
        this.isApproved = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.commentList = new ArrayList<CommentBean>();
        in.readList(this.commentList, CommentBean.class.getClassLoader());
//        this.approveList = in.createTypedArrayList(UserBean.CREATOR);
    }

    public static final Creator<MomentBean> CREATOR = new Creator<MomentBean>() {
        @Override
        public MomentBean createFromParcel(Parcel source) {
            return new MomentBean(source);
        }

        @Override
        public MomentBean[] newArray(int size) {
            return new MomentBean[size];
        }
    };
}
