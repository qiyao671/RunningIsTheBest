package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.qiyao.bysj.baselibrary.common.utils.EmptyUtils;
import com.qiyao.bysj.baselibrary.common.utils.LocationUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.dao.RunDao;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunFragment;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        RunDao runDao = new RunDao(fragment.getActivity());
        List<RunBean> runBeen = runDao.listByUserId(SPHelper.loadUser().getId());
        if (EmptyUtils.isEmpty(runBeen)) return;
        HttpMethods.getInstance()
                .uploadRunRecordList(runBeen)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .subscribe(ToastUtils::showShortToast,
                        e -> ToastUtils.showShortToast(e.getMessage()),
                        () -> runDao.removeList(runBeen));
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
