package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.qiyao.bysj.baselibrary.common.utils.LocationUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.dao.RunDao;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunFragment;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 23:03.
 * 类描述：
 */

public class StartRunViewModel implements IViewModel {
    private Fragment fragment;

    public StartRunViewModel(Fragment fragment) {
        this.fragment = fragment;
        uploadLocalRecord();
    }

    private void uploadLocalRecord() {
        List<RunBean> runBeen = new RunDao(fragment.getActivity()).listByUserId(SPHelper.loadUser().getId());
        List<RunBean> runBeen1 = new RunDao(fragment.getActivity()).listAll();
    }

    public void onClick(View v) {
        if (LocationUtils.isGpsEnabled()) {
            RunFragment.newInstance(fragment.getActivity());
        } else {
            new MaterialDialog.Builder(fragment.getActivity())
                    .content(R.string.hint_open_gps)
                    .positiveText(R.string.goto_set)
                    .positiveColor(ContextCompat.getColor(fragment.getActivity(), R.color.accent))
                    .onPositive((dialog, which) -> LocationUtils.openGpsSettings())
                    .negativeText(R.string.cancel)
                    .negativeColor(ContextCompat.getColor(fragment.getActivity(), R.color.secondary_text))
                    .onNegative((dialog, which) -> ToastUtils.showLongToast(R.string.pls_open_gps))
                    .show();
        }
    }
}
