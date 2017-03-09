package com.qiyao.bysj.runningisthebest.model.bean;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/6 20:37.
 * 类描述：
 */

public class BestRunBean {

    /**
     * farthestLogInfo : {"id":4,"userId":1,"distance":12,"spendTime":70,"energy":180.9,"createTime":1488887354000,"picture":null,"momentContent":"牛逼啊","totalDistance":null,"totalSpendTime":null,"totalCount":null,"totalEnergy":null,"maxDistance":null,"minDistance":null}
     * longestRunLogVO : {"id":4,"userId":1,"distance":12,"spendTime":70,"energy":180.9,"createTime":1488887354000,"picture":null,"momentContent":"牛逼啊","totalDistance":null,"totalSpendTime":null,"totalCount":null,"totalEnergy":null,"maxDistance":null,"minDistance":null}
     * fullMaPB : null
     * fivePB : {"id":2,"userId":1,"distance":5.38,"spendTime":30.87,"energy":100.5,"createTime":1488701590000,"picture":null,"momentContent":"这速度我自己都害怕","totalDistance":null,"totalSpendTime":null,"totalCount":null,"totalEnergy":null,"maxDistance":null,"minDistance":null}
     * halfMaPB : null
     * tenPB : {"id":1,"userId":1,"distance":10.21,"spendTime":60.41,"energy":192.4,"createTime":1488701534000,"picture":null,"momentContent":"跑的我低血糖","totalDistance":null,"totalSpendTime":null,"totalCount":null,"totalEnergy":null,"maxDistance":null,"minDistance":null}
     */

    private RunBean farthestLogInfo;
    private RunBean longestRunLogVO;
    private RunBean fullMaPB;
    private RunBean fivePB;
    private RunBean halfMaPB;
    private RunBean tenPB;

    public RunBean getFarthestLogInfo() {
        return farthestLogInfo;
    }

    public void setFarthestLogInfo(RunBean farthestLogInfo) {
        this.farthestLogInfo = farthestLogInfo;
    }

    public RunBean getLongestRunLogVO() {
        return longestRunLogVO;
    }

    public void setLongestRunLogVO(RunBean longestRunLogVO) {
        this.longestRunLogVO = longestRunLogVO;
    }

    public RunBean getFullMaPB() {
        return fullMaPB;
    }

    public void setFullMaPB(RunBean fullMaPB) {
        this.fullMaPB = fullMaPB;
    }

    public RunBean getFivePB() {
        return fivePB;
    }

    public void setFivePB(RunBean fivePB) {
        this.fivePB = fivePB;
    }

    public RunBean getHalfMaPB() {
        return halfMaPB;
    }

    public void setHalfMaPB(RunBean halfMaPB) {
        this.halfMaPB = halfMaPB;
    }

    public RunBean getTenPB() {
        return tenPB;
    }

    public void setTenPB(RunBean tenPB) {
        this.tenPB = tenPB;
    }
}
