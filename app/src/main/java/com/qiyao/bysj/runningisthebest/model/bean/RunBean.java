package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.maps.model.LatLng;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/9 21:00.
 * 类描述：
 */

public class RunBean implements Parcelable {
    private Integer id;

    private Integer userId;

    private Double distance;

    private Long spendTime;

    private Double energy;

    private Long createTime;

    private String picture;

    private String momentContent;

    private Long startRunTime;

    private List<List<LatLng>> tracks;

    private List<List<Double>> altitudeLists;

    private UserBean user;

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMomentContent() {
        return momentContent;
    }

    public void setMomentContent(String momentContent) {
        this.momentContent = momentContent;
    }

    public Long getStartRunTime() {
        return startRunTime;
    }

    public void setStartRunTime(Long startRunTime) {
        this.startRunTime = startRunTime;
    }

    public RunBean() {
    }

    public List<List<LatLng>> getTracks() {
        return tracks;
    }

    public void setTracks(List<List<LatLng>> tracks) {
        this.tracks = tracks;
    }

    public List<List<Double>> getAltitudeLists() {
        return altitudeLists;
    }

    public void setAltitudeLists(List<List<Double>> altitudeLists) {
        this.altitudeLists = altitudeLists;
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
        dest.writeValue(this.userId);
        dest.writeValue(this.distance);
        dest.writeValue(this.spendTime);
        dest.writeValue(this.energy);
        dest.writeValue(this.createTime);
        dest.writeString(this.picture);
        dest.writeString(this.momentContent);
        dest.writeValue(this.startRunTime);
        dest.writeParcelable(this.user, flags);
    }

    protected RunBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distance = (Double) in.readValue(Double.class.getClassLoader());
        this.spendTime = (Long) in.readValue(Long.class.getClassLoader());
        this.energy = (Double) in.readValue(Double.class.getClassLoader());
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.picture = in.readString();
        this.momentContent = in.readString();
        this.startRunTime = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Creator<RunBean> CREATOR = new Creator<RunBean>() {
        @Override
        public RunBean createFromParcel(Parcel source) {
            return new RunBean(source);
        }

        @Override
        public RunBean[] newArray(int size) {
            return new RunBean[size];
        }
    };
}
