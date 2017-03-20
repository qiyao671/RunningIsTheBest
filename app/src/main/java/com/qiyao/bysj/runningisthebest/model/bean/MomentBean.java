package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

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

    /*非数据库字段*/
    private Integer minId;

    private Integer maxId;

    private Integer num;

    private Integer pageSize;

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

    public Integer getMinId() {
        return minId;
    }

    public void setMinId(Integer minId) {
        this.minId = minId;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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
        dest.writeValue(this.minId);
        dest.writeValue(this.maxId);
        dest.writeValue(this.num);
        dest.writeValue(this.pageSize);
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
        this.minId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.maxId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.num = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageSize = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<MomentBean> CREATOR = new Parcelable.Creator<MomentBean>() {
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
