package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

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

    /*非数据库字段*/
    private Double totalDistance;

    private Long totalSpendTime;

    private Double totalCount;

    private Integer totalEnergy;

    private Double maxDistance;

    private Double minDistance;

    private Double fastSpend;
    /*最快配速*/
    private Double fastPace;

    private Long beginTime;

    private Long endTime;

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
        this.picture = picture == null ? null : picture.trim();
    }

    public String getMomentContent() {
        return momentContent;
    }

    public void setMomentContent(String momentContent) {
        this.momentContent = momentContent == null ? null : momentContent.trim();
    }

    public Integer getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(Integer totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Long getTotalSpendTime() {
        return totalSpendTime;
    }

    public void setTotalSpendTime(Long totalSpendTime) {
        this.totalSpendTime = totalSpendTime;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Double minDistance) {
        this.minDistance = minDistance;
    }

    public Double getFastSpend() {
        return fastSpend;
    }

    public void setFastSpend(Double fastSpend) {
        this.fastSpend = fastSpend;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Double getFastPace() {
        return fastPace;
    }

    public void setFastPace(Double fastPace) {
        this.fastPace = fastPace;
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
        dest.writeValue(this.totalDistance);
        dest.writeValue(this.totalSpendTime);
        dest.writeValue(this.totalCount);
        dest.writeValue(this.totalEnergy);
        dest.writeValue(this.maxDistance);
        dest.writeValue(this.minDistance);
        dest.writeValue(this.fastSpend);
        dest.writeValue(this.fastPace);
        dest.writeValue(this.beginTime);
        dest.writeValue(this.endTime);
    }

    public RunBean() {
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
        this.totalDistance = (Double) in.readValue(Double.class.getClassLoader());
        this.totalSpendTime = (Long) in.readValue(Long.class.getClassLoader());
        this.totalCount = (Double) in.readValue(Double.class.getClassLoader());
        this.totalEnergy = (Integer) in.readValue(Integer.class.getClassLoader());
        this.maxDistance = (Double) in.readValue(Double.class.getClassLoader());
        this.minDistance = (Double) in.readValue(Double.class.getClassLoader());
        this.fastSpend = (Double) in.readValue(Double.class.getClassLoader());
        this.fastPace = (Double) in.readValue(Double.class.getClassLoader());
        this.beginTime = (Long) in.readValue(Long.class.getClassLoader());
        this.endTime = (Long) in.readValue(Long.class.getClassLoader());
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
