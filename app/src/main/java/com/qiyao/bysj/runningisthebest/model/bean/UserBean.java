package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 16:48.
 * 类描述：
 */

public class UserBean implements Parcelable {

    /**
     * id : 1
     * username : xiaohong
     * password : 123456
     * rank : 2
     * profile : https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4225565668,46745471&fm=80&w=179&h=119&img.JPEG
     * sex : 女
     * height : 180.0
     * weight : 98.0
     * birthday : 1488284962000
     * location : 浙江省宁波市鄞州区
     * communityId : 12
     * totalRunId : 3
     * bestRunId : 13
     * signature : null
     */

    private int id;
    private String username;
    private String password;
    private int rank;
    private String profile;
    private String sex;
    private double height;
    private double weight;
    private Long birthday;
    private String location;
    private int communityId;
    private int totalRunId;
    private int bestRunId;
    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = new Long(birthday);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getTotalRunId() {
        return totalRunId;
    }

    public void setTotalRunId(int totalRunId) {
        this.totalRunId = totalRunId;
    }

    public int getBestRunId() {
        return bestRunId;
    }

    public void setBestRunId(int bestRunId) {
        this.bestRunId = bestRunId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeInt(this.rank);
        dest.writeString(this.profile);
        dest.writeString(this.sex);
        dest.writeDouble(this.height);
        dest.writeDouble(this.weight);
        dest.writeLong(this.birthday != null ? this.birthday : -1);
        dest.writeString(this.location);
        dest.writeInt(this.communityId);
        dest.writeInt(this.totalRunId);
        dest.writeInt(this.bestRunId);
        dest.writeString(this.signature);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.password = in.readString();
        this.rank = in.readInt();
        this.profile = in.readString();
        this.sex = in.readString();
        this.height = in.readDouble();
        this.weight = in.readDouble();
        long tmpBirthday = in.readLong();
        this.birthday = tmpBirthday == -1 ? null : tmpBirthday;
        this.location = in.readString();
        this.communityId = in.readInt();
        this.totalRunId = in.readInt();
        this.bestRunId = in.readInt();
        this.signature = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
