package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.BestRunFragment;
import com.qiyao.bysj.runningisthebest.module.home.ui.SettingFragment;
import com.qiyao.bysj.runningisthebest.module.home.ui.TotalRunPagerFragment;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunRecordsFragment;
import com.trello.rxlifecycle.components.RxFragment;

import me.tatarka.bindingcollectionadapter2.OnItemBind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/3 21:57.
 * 类描述：
 */

public class HomeViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    public ObservableField<String> totalTimes = new ObservableField<>("--");
    public ObservableField<String> totalDistance = new ObservableField<>("--");
    public ObservableField<String> totalDuration = new ObservableField<>("--");

    private TotalRunBean totalRunBean;

    public ObservableArrayList<MyInfoItemViewModel> myInfoItems = new ObservableArrayList<>();

    private OnItemBind itemView;

    public HomeViewModel(Fragment fragment) {
        this.fragment = fragment;
        MyInfoItemViewModel myInfoItemViewModel = new MyInfoItemViewModel(fragment);
        myInfoItems.add(myInfoItemViewModel);
        myInfoItems.add(myInfoItemViewModel);
        itemView = (itemBinding, position, item) ->
                itemBinding.set(BR.viewModel, position == 0 ? R.layout.item_my_base_info : R.layout.item_my_more_info);
        getTotalRun();
    }

    private void getTotalRun() {
        HttpMethods.getInstance()
                .getTotalLogInfo()
                .subscribeOn(Schedulers.newThread())
                .filter(totalRun -> totalRun != null)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((RxFragment) fragment).bindToLifecycle())
                .subscribe(this::setTotalRunInfo, this::onError);
    }

    private void onError(Throwable e) {
        Log.e("home", "onError: " + e.getMessage(), e);
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
                break;
            case R.id.history:
                FragmentContainerActivity.launch(fragment.getActivity(), RunRecordsFragment.class);
        }
    }

    @Bindable
    public OnItemBind getItemView() {
        return itemView;
    }
}
