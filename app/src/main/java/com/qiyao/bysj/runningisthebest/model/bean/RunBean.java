package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/9 21:00.
 * 类描述：
 */

@DatabaseTable(tableName = "tb_user")
public class RunBean implements Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "user_id")
    private Integer userId;

    @DatabaseField(columnName = "distance")
    private Double distance;

    @DatabaseField(columnName = "spend_time")
    private Long spendTime;

    private Double energy;

    private Long createTime;

    private String picture;

    private String momentContent;

    @DatabaseField(columnName = "start_run_time")
    private Long startRunTime;

    @DatabaseField(columnName = "tracks", dataType = DataType.SERIALIZABLE)
    private ArrayList<ArrayList<HashMap<String, Double>>> tracks;

    @DatabaseField(columnName = "altitudeLists", dataType = DataType.SERIALIZABLE)
    private ArrayList<ArrayList<Double>> altitudeLists;

    private UserBean user;

    private Double totalDistance;

    private Long totalSpendTime;

    private Double totalCount;

    private Integer totalEnergy;

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

    public Integer getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(Integer totalEnergy) {
        this.totalEnergy = totalEnergy;
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
        dest.writeValue(this.totalDistance);
        dest.writeValue(this.totalSpendTime);
        dest.writeValue(this.totalCount);
        dest.writeValue(this.totalEnergy);
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
        this.startRunTime = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.totalDistance = (Double) in.readValue(Double.class.getClassLoader());
        this.totalSpendTime = (Long) in.readValue(Long.class.getClassLoader());
        this.totalCount = (Double) in.readValue(Double.class.getClassLoader());
        this.totalEnergy = (Integer) in.readValue(Integer.class.getClassLoader());
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

    public ArrayList<ArrayList<LatLng>> getTracks() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(tracks), new TypeToken<ArrayList<ArrayList<LatLng>>>(){}.getType());
    }

    public void setTracks(ArrayList<ArrayList<LatLng>> tracks) {
        Gson gson = new Gson();
        this.tracks = gson.fromJson(gson.toJson(tracks), new TypeToken<ArrayList<ArrayList<HashMap<String, Double>>>>(){}.getType());
    }

    public ArrayList<ArrayList<Double>> getAltitudeLists() {
        return altitudeLists;
    }

    public void setAltitudeLists(ArrayList<ArrayList<Double>> altitudeLists) {
        this.altitudeLists = altitudeLists;
    }
}
