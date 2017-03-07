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

    private FarthestLogInfoBean farthestLogInfo;
    private LongestRunLogVOBean longestRunLogVO;
    private Object fullMaPB;
    private FivePBBean fivePB;
    private Object halfMaPB;
    private TenPBBean tenPB;

    public FarthestLogInfoBean getFarthestLogInfo() {
        return farthestLogInfo;
    }

    public void setFarthestLogInfo(FarthestLogInfoBean farthestLogInfo) {
        this.farthestLogInfo = farthestLogInfo;
    }

    public LongestRunLogVOBean getLongestRunLogVO() {
        return longestRunLogVO;
    }

    public void setLongestRunLogVO(LongestRunLogVOBean longestRunLogVO) {
        this.longestRunLogVO = longestRunLogVO;
    }

    public Object getFullMaPB() {
        return fullMaPB;
    }

    public void setFullMaPB(Object fullMaPB) {
        this.fullMaPB = fullMaPB;
    }

    public FivePBBean getFivePB() {
        return fivePB;
    }

    public void setFivePB(FivePBBean fivePB) {
        this.fivePB = fivePB;
    }

    public Object getHalfMaPB() {
        return halfMaPB;
    }

    public void setHalfMaPB(Object halfMaPB) {
        this.halfMaPB = halfMaPB;
    }

    public TenPBBean getTenPB() {
        return tenPB;
    }

    public void setTenPB(TenPBBean tenPB) {
        this.tenPB = tenPB;
    }

    public static class FarthestLogInfoBean {
        /**
         * id : 4
         * userId : 1
         * distance : 12.0
         * spendTime : 70.0
         * energy : 180.9
         * createTime : 1488887354000
         * picture : null
         * momentContent : 牛逼啊
         * totalDistance : null
         * totalSpendTime : null
         * totalCount : null
         * totalEnergy : null
         * maxDistance : null
         * minDistance : null
         */

        private int id;
        private int userId;
        private double distance;
        private double spendTime;
        private double energy;
        private long createTime;
        private Object picture;
        private String momentContent;
        private Object totalDistance;
        private Object totalSpendTime;
        private Object totalCount;
        private Object totalEnergy;
        private Object maxDistance;
        private Object minDistance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(double spendTime) {
            this.spendTime = spendTime;
        }

        public double getEnergy() {
            return energy;
        }

        public void setEnergy(double energy) {
            this.energy = energy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public String getMomentContent() {
            return momentContent;
        }

        public void setMomentContent(String momentContent) {
            this.momentContent = momentContent;
        }

        public Object getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(Object totalDistance) {
            this.totalDistance = totalDistance;
        }

        public Object getTotalSpendTime() {
            return totalSpendTime;
        }

        public void setTotalSpendTime(Object totalSpendTime) {
            this.totalSpendTime = totalSpendTime;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public Object getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(Object totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public Object getMaxDistance() {
            return maxDistance;
        }

        public void setMaxDistance(Object maxDistance) {
            this.maxDistance = maxDistance;
        }

        public Object getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(Object minDistance) {
            this.minDistance = minDistance;
        }
    }

    public static class LongestRunLogVOBean {
        /**
         * id : 4
         * userId : 1
         * distance : 12.0
         * spendTime : 70.0
         * energy : 180.9
         * createTime : 1488887354000
         * picture : null
         * momentContent : 牛逼啊
         * totalDistance : null
         * totalSpendTime : null
         * totalCount : null
         * totalEnergy : null
         * maxDistance : null
         * minDistance : null
         */

        private int id;
        private int userId;
        private double distance;
        private double spendTime;
        private double energy;
        private long createTime;
        private Object picture;
        private String momentContent;
        private Object totalDistance;
        private Object totalSpendTime;
        private Object totalCount;
        private Object totalEnergy;
        private Object maxDistance;
        private Object minDistance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(double spendTime) {
            this.spendTime = spendTime;
        }

        public double getEnergy() {
            return energy;
        }

        public void setEnergy(double energy) {
            this.energy = energy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public String getMomentContent() {
            return momentContent;
        }

        public void setMomentContent(String momentContent) {
            this.momentContent = momentContent;
        }

        public Object getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(Object totalDistance) {
            this.totalDistance = totalDistance;
        }

        public Object getTotalSpendTime() {
            return totalSpendTime;
        }

        public void setTotalSpendTime(Object totalSpendTime) {
            this.totalSpendTime = totalSpendTime;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public Object getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(Object totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public Object getMaxDistance() {
            return maxDistance;
        }

        public void setMaxDistance(Object maxDistance) {
            this.maxDistance = maxDistance;
        }

        public Object getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(Object minDistance) {
            this.minDistance = minDistance;
        }
    }

    public static class FivePBBean {
        /**
         * id : 2
         * userId : 1
         * distance : 5.38
         * spendTime : 30.87
         * energy : 100.5
         * createTime : 1488701590000
         * picture : null
         * momentContent : 这速度我自己都害怕
         * totalDistance : null
         * totalSpendTime : null
         * totalCount : null
         * totalEnergy : null
         * maxDistance : null
         * minDistance : null
         */

        private int id;
        private int userId;
        private double distance;
        private double spendTime;
        private double energy;
        private long createTime;
        private Object picture;
        private String momentContent;
        private Object totalDistance;
        private Object totalSpendTime;
        private Object totalCount;
        private Object totalEnergy;
        private Object maxDistance;
        private Object minDistance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(double spendTime) {
            this.spendTime = spendTime;
        }

        public double getEnergy() {
            return energy;
        }

        public void setEnergy(double energy) {
            this.energy = energy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public String getMomentContent() {
            return momentContent;
        }

        public void setMomentContent(String momentContent) {
            this.momentContent = momentContent;
        }

        public Object getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(Object totalDistance) {
            this.totalDistance = totalDistance;
        }

        public Object getTotalSpendTime() {
            return totalSpendTime;
        }

        public void setTotalSpendTime(Object totalSpendTime) {
            this.totalSpendTime = totalSpendTime;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public Object getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(Object totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public Object getMaxDistance() {
            return maxDistance;
        }

        public void setMaxDistance(Object maxDistance) {
            this.maxDistance = maxDistance;
        }

        public Object getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(Object minDistance) {
            this.minDistance = minDistance;
        }
    }

    public static class TenPBBean {
        /**
         * id : 1
         * userId : 1
         * distance : 10.21
         * spendTime : 60.41
         * energy : 192.4
         * createTime : 1488701534000
         * picture : null
         * momentContent : 跑的我低血糖
         * totalDistance : null
         * totalSpendTime : null
         * totalCount : null
         * totalEnergy : null
         * maxDistance : null
         * minDistance : null
         */

        private int id;
        private int userId;
        private double distance;
        private double spendTime;
        private double energy;
        private long createTime;
        private Object picture;
        private String momentContent;
        private Object totalDistance;
        private Object totalSpendTime;
        private Object totalCount;
        private Object totalEnergy;
        private Object maxDistance;
        private Object minDistance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(double spendTime) {
            this.spendTime = spendTime;
        }

        public double getEnergy() {
            return energy;
        }

        public void setEnergy(double energy) {
            this.energy = energy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public String getMomentContent() {
            return momentContent;
        }

        public void setMomentContent(String momentContent) {
            this.momentContent = momentContent;
        }

        public Object getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(Object totalDistance) {
            this.totalDistance = totalDistance;
        }

        public Object getTotalSpendTime() {
            return totalSpendTime;
        }

        public void setTotalSpendTime(Object totalSpendTime) {
            this.totalSpendTime = totalSpendTime;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public Object getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(Object totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public Object getMaxDistance() {
            return maxDistance;
        }

        public void setMaxDistance(Object maxDistance) {
            this.maxDistance = maxDistance;
        }

        public Object getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(Object minDistance) {
            this.minDistance = minDistance;
        }
    }
}
