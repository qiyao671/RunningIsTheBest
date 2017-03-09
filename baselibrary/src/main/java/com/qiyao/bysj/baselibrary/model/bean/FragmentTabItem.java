package com.qiyao.bysj.baselibrary.model.bean;

import android.app.Fragment;

/**
 * Created by qiyao on 2017/3/9.
 */

public class FragmentTabItem extends TabItem {
    private Class<? extends Fragment> fragmentClass;

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }
}
