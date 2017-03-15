package com.qiyao.bysj.runningisthebest.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
     * height : 180
     * weight : 98
     * birthday : 1488284962000
     * location : 浙江省宁波市鄞州区
     * communityId : 12
     * totalRunId : 3
     * bestRunId : 13
     * signature :
     * age : 12
     */

    private Integer id;

    private String username;

    private String password;

    private Integer rank;

    private String profile;

    private String sex;

    private Double height;

    private Double weight;

    private Long birthday;

    private String location;

    private Integer communityId;

    private Integer totalRunId;

    private Integer bestRunId;

    private String signature;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getTotalRunId() {
        return totalRunId;
    }

    public void setTotalRunId(Integer totalRunId) {
        this.totalRunId = totalRunId;
    }

    public Integer getBestRunId() {
        return bestRunId;
    }

    public void setBestRunId(Integer bestRunId) {
        this.bestRunId = bestRunId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeValue(this.rank);
        dest.writeString(this.profile);
        dest.writeString(this.sex);
        dest.writeValue(this.height);
        dest.writeValue(this.weight);
        dest.writeValue(this.birthday);
        dest.writeString(this.location);
        dest.writeValue(this.communityId);
        dest.writeValue(this.totalRunId);
        dest.writeValue(this.bestRunId);
        dest.writeString(this.signature);
        dest.writeValue(this.age);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.username = in.readString();
        this.password = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.profile = in.readString();
        this.sex = in.readString();
        this.height = (Double) in.readValue(Double.class.getClassLoader());
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.birthday = (Long) in.readValue(Long.class.getClassLoader());
        this.location = in.readString();
        this.communityId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalRunId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bestRunId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.signature = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
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
