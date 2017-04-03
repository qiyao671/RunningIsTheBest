package com.qiyao.bysj.runningisthebest.model.bean;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/8 22:07.
 * 类描述：
 */

public class TotalRunBean {

    /**
     * totalTimes : 4
     * totalEnergy : 624
     * totalDistance : 36.39
     * totalSpendTime : 206.66
     */

    private int totalTimes;
    private double totalEnergy;
    private double totalDistance;
    private long totalSpendTime;

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(double totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public long getTotalSpendTime() {
        return totalSpendTime;
    }

    public void setTotalSpendTime(long totalSpendTime) {
        this.totalSpendTime = totalSpendTime;
    }
}
