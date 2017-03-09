package com.qiyao.bysj.baselibrary.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * ViewPager的Tab页标签
 *
 * Created by wangdan on 15/12/22.
 */
public class TabItem implements Parcelable {

    private static final long serialVersionUID = -1162756298239591517L;

    private String type;

    private String title;

    private Parcelable tag;

    public TabItem() {

    }
    public TabItem(String title) {
        this.title = title;
    }
    public TabItem(String type, String title) {
        this.type = type;
        this.title = title;
    }
    public TabItem(String title, Parcelable tag) {
        this.title = title;
        this.tag = tag;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Parcelable getTag() {
        return tag;
    }

    public void setTag(Parcelable tag) {
        this.tag = tag;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeParcelable(this.tag, flags);
    }

    protected TabItem(Parcel in) {
        this.type = in.readString();
        this.title = in.readString();
        this.tag = in.readParcelable(Parcelable.class.getClassLoader());
    }

    public static final Parcelable.Creator<TabItem> CREATOR = new Parcelable.Creator<TabItem>() {
        @Override
        public TabItem createFromParcel(Parcel source) {
            return new TabItem(source);
        }

        @Override
        public TabItem[] newArray(int size) {
            return new TabItem[size];
        }
    };
}
