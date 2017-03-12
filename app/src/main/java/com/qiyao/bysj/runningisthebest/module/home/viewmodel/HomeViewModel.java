package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.BestRunFragment;
import com.qiyao.bysj.runningisthebest.module.home.ui.SettingFragment;
import com.qiyao.bysj.runningisthebest.module.home.ui.TotalRunPagerFragment;
import com.trello.rxlifecycle.components.RxFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/3 21:57.
 * 类描述：
 */

public class HomeViewModel implements IViewModel {
    private Fragment fragment;

    public ObservableField<String> userProfileUrl = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userId = new ObservableField<>();
    public ObservableField<String> sexAndAge = new ObservableField<>();
    public ObservableField<String> signature = new ObservableField<>();

    public ObservableField<String> totalTimes = new ObservableField<>("--");
    public ObservableField<String> totalDistance = new ObservableField<>("--");
    public ObservableField<String> totalDuration = new ObservableField<>("--");

    private UserBean userBean;
    private TotalRunBean totalRunBean;

    private HttpMethods httpMethods;

    public HomeViewModel(Fragment fragment) {
        this.fragment = fragment;
        httpMethods = HttpMethods.getInstance();
        //默认显示缓存的数据
        UserBean oldUser = SPHelper.loadUser();
        if (oldUser != null) {
            setUserInfo(oldUser);
        }
        getUser();
        getTotalRun();
    }

    private void getUser() {
        httpMethods.getUserBean()
                .subscribeOn(Schedulers.newThread())
                .filter(user -> user != null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(SPHelper::saveUser)
                .compose(((RxFragment) fragment).bindToLifecycle())
                .subscribe(this::setUserInfo, this::onError);
    }

    private void getTotalRun() {
        httpMethods.getTotalLogInfo()
                .subscribeOn(Schedulers.newThread())
                .filter(totalRun -> totalRun != null)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((RxFragment) fragment).bindToLifecycle())
                .subscribe(this::setTotalRunInfo, this::onError);
    }

    private void onError(Throwable e) {
        Log.e("home", "onError: " + e.getMessage(), e);
    }

    private void setUserInfo(UserBean user) {
        this.userBean = user;
        // TODO: 2017/3/10 不一样的时候再改
        userProfileUrl.set(user.getProfile());
        userId.set(String.valueOf(user.getId()));
        userName.set(user.getUsername());
        if (!StringUtils.isEmpty(user.getSex())) {
            String str = (fragment.getString(R.string.sex_And_Age));
            // TODO: 2017/3/8 age
            sexAndAge.set(String.format(str, user.getSex(), 13));
        } else {
            // TODO: 2017/3/8 age
            sexAndAge.set("0岁");
        }
        signature.set(user.getSignature());
    }

    private void setTotalRunInfo(TotalRunBean totalRun) {
        this.totalRunBean = totalRun;

        totalDistance.set(String.valueOf(totalRun.getTotalDistance()));
        totalDuration.set(String.valueOf(totalRun.getTotalSpendTime()));
        totalTimes.set(String.valueOf(totalRun.getTotalTimes()));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.best_run:
                BestRunFragment.launch(fragment.getActivity());
                break;
            case R.id.total_run:
                TotalRunPagerFragment.launch(fragment.getActivity());
                break;
            case R.id.setting:
                SettingFragment.launch(fragment.getActivity());
        }
    }
}
