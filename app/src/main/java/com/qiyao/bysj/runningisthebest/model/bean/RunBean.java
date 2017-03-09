package com.qiyao.bysj.runningisthebest.model.bean;

import java.util.Date;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/9 21:00.
 * 类描述：
 */

public class RunBean {
    private Integer id;

    private Integer userId;

    private Double distance;

    private Double spendTime;

    private Double energy;

    private Date createTime;

    private String picture;

    private String momentContent;

    private Double totalDistance;

    private Double totalSpendTime;

    private Double totalCount;

    private Integer totalEnergy;

    private Double maxDistance;

    private Double minDistance;

    private Double fastSpend;

    private Date beginTime;

    private Date endTime;

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

    public Double getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Double spendTime) {
        this.spendTime = spendTime;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Double getTotalSpendTime() {
        return totalSpendTime;
    }

    public void setTotalSpendTime(Double totalSpendTime) {
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
