package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunRecordDetailPagerFragment;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsItemViewModel implements IItemViewModel {
    private Fragment fragment;

    public ObservableField<String> distance = new ObservableField<>();
    public ObservableField<String> duration = new ObservableField<>();
    public ObservableField<String> datetime = new ObservableField<>();
    public ObservableField<String> avgPace = new ObservableField<>();
    private RunBean runBean;

    public RunRecordsItemViewModel(Fragment fragment, RunBean runBean) {
        this.fragment = fragment;
        this.runBean = runBean;
        setRunInfo(runBean);
    }

    private void setRunInfo(RunBean runBean) {
        distance.set(runBean.getDistance() != null ? String.valueOf(runBean.getDistance()) : "--");
        duration.set(runBean.getSpendTime() != null ? MyAppUtils.getTime(runBean.getSpendTime()): "--");
        datetime.set(runBean.getBeginTime() != null ? TimeUtils.getFriendlyTimeSpanByNow(runBean.getBeginTime()) : "--");
        if (!duration.get().equals("--") && !distance.get().equals("--")) {
            avgPace.set(MyAppUtils.getPace(runBean.getSpendTime(), runBean.getDistance()));
        }
    }

    public void onClick(View view) {
        RunRecordDetailPagerFragment.launch(fragment.getActivity(), runBean);
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }
}
