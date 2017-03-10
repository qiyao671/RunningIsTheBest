package com.qiyao.bysj.baselibrary.model.bean;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiyao on 2017/3/9.
 */

public class FragmentTabItem extends TabItem {
    public FragmentTabItem(String title, Class<? extends Fragment> fragmentClass) {
        super(title);
        this.fragmentClass = fragmentClass;
    }

    public FragmentTabItem(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public FragmentTabItem(String type, String title, Class<? extends Fragment> fragmentClass) {
        super(type, title);
        this.fragmentClass = fragmentClass;
    }

    public FragmentTabItem(Parcel in, Class<? extends Fragment> fragmentClass) {
        super(in);
        this.fragmentClass = fragmentClass;
    }

    public FragmentTabItem(String title, Parcelable tag, Class<? extends Fragment> fragmentClass) {

        super(title, tag);
        this.fragmentClass = fragmentClass;
    }

    private Class<? extends Fragment> fragmentClass;

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }
}
