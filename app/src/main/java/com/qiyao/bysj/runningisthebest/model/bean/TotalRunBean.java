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
    private int totalEnergy;
    private double totalDistance;
    private double totalSpendTime;

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalSpendTime() {
        return totalSpendTime;
    }

    public void setTotalSpendTime(double totalSpendTime) {
        this.totalSpendTime = totalSpendTime;
    }
}
